package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat

import com.github.cao.awa.majalis.config.MajalisConfigs
import com.github.cao.awa.majalis.env.human.organ.heart.HumanHeart
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pipeline.WaveMetadataProducer
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p.PWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p.PWaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.segmnent.PRSegmentWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.segmnent.PRSegmentWaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.QRSWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.QRSWaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.q.QWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.q.QWaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.r.RWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.r.RWaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.s.SWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.s.SWaveTrigger
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries
import com.github.cao.awa.majalis.env.tick.Tickable
import java.util.function.BiConsumer
import java.util.function.Consumer

class HeartbeatTrigger(heart: HumanHeart, arteries: HumanArteries) : Tickable {
    private val prWave = PRWaveTrigger()
    private val pWave = PWaveTrigger()
    private val prSegment = PRSegmentWaveTrigger()
    private val qrsWave = QRSWaveTrigger(arteries)
    private val qWave = QWaveTrigger()
    private val rWave = RWaveTrigger()
    private val sWave = SWaveTrigger()
    private var sinus = true
    private var finished = false
    private var times = 0

    init {
        prMetaProcessor { pr ->
            pr.troughTime(this.times)
        }.pMetaProcessor { p ->
            p.troughTime(this.times)
        }.prSegmentMetaProcessor { prSegment ->
            prSegment.troughTime(this.times)
        }.qrsMetaProcessor { qrs ->
            qrs.troughTime(this.times)
        }.qMetaProcessor { q ->
            q.troughTime(this.times)
        }.rMetaProcessor { r ->
            r.troughTime(this.times)
        }.sMetaProcessor { s ->
            s.troughTime(this.times)
        }

        pForward { p, pr -> pr.pMeta(p) }

        prSegmentForward { prSegment, pr -> pr.prSegmentMeta(prSegment) }

        qrsForward(QRSWaveMetadata::prMeta)

        qForward { q, qrs -> qrs.qMeta(q) }

        rForward { r, qrs -> qrs.rMeta(r) }

        sForward { s, qrs -> qrs.sMeta(s) }
    }

    fun times(): Int = this.times

    override fun tick() {
        if (this.sinus) {
            tickSinus()
        }
    }

    private fun tickSinus() {
        if (this.times in 0..159) {
            this.prWave.trigger()

            if (this.times < 100) {
                this.pWave.forwardFromTrigger(this.prWave)
            } else if (this.times == 100) {
                this.pWave.finish(this.times)
            }

            if (this.times in 100..159) {
                this.prSegment.forwardFromTrigger(this.prWave)
            }
        } else if (this.times == 160) {
            this.prWave.finish(this.times)
            this.prSegment.finish(this.times)
        }

        if (this.times in 160..239) {
            this.qrsWave.forwardFromTrigger(this.prWave)

            if (this.times < 180) {
                this.qWave.forwardFromTrigger(this.qrsWave)
            } else if (this.times == 180) {
                this.qWave.finish(this.times)
            }

            if (this.times in 180..199) {
                this.rWave.forwardFromTrigger(this.qrsWave)
            } else if (this.times == 200) {
                this.rWave.finish(this.times)
            }

            if (this.times in 200..219) {
                this.sWave.forwardFromTrigger(this.qrsWave)
            } else if (this.times == 220) {
                this.sWave.finish(this.times)
            }
        } else if (this.times == 240) {
            this.qrsWave.finish(this.times)
        }

        if (this.times >= 240) {
            this.finished = true
        } else {
            this.finished = false
        }

        this.times += MajalisConfigs.expectedTickTime
    }

    fun finished(): Boolean = this.finished

    fun finished(trigger: Consumer<HeartbeatTrigger>) {
        if (this.finished) {
            trigger.accept(this)
        }
    }

    fun finished(trigger: Runnable) {
        if (this.finished) {
            trigger.run()
        }
    }

    fun resetHeartbeat(): HeartbeatTrigger {
        this.times = 0
        this.finished = false
        return this
    }

    fun sinus(sinus: Boolean): HeartbeatTrigger {
        this.sinus = sinus
        return this
    }

    fun sinus(): Boolean = this.sinus

    fun prSubscribe(action: Consumer<PRWaveMetadata>): HeartbeatTrigger {
        this.prWave.trigger(action)
        return this
    }

    fun prMetaProcessor(action: WaveMetadataProducer<PRWaveMetadata>): HeartbeatTrigger {
        this.prWave.metadataProcessor(action)
        return this
    }

    fun prForward(action: BiConsumer<PRWaveMetadata, WaveMetadata>): HeartbeatTrigger {
        this.prWave.metadataForwarder(action)
        return this
    }

    fun prSegmentSubscribe(action: Consumer<PRSegmentWaveMetadata>): HeartbeatTrigger {
        this.prSegment.trigger(action)
        return this
    }

    fun prSegmentMetaProcessor(action: WaveMetadataProducer<PRSegmentWaveMetadata>): HeartbeatTrigger {
        this.prSegment.metadataProcessor(action)
        return this
    }

    fun prSegmentForward(action: BiConsumer<PRSegmentWaveMetadata, PRWaveMetadata>): HeartbeatTrigger {
        this.prSegment.metadataForwarder(action)
        return this
    }

    fun pSubscribe(action: Consumer<PWaveMetadata>): HeartbeatTrigger {
        this.pWave.trigger(action)
        return this
    }

    fun pMetaProcessor(action: WaveMetadataProducer<PWaveMetadata>): HeartbeatTrigger {
        this.pWave.metadataProcessor(action)
        return this
    }

    fun pForward(action: BiConsumer<PWaveMetadata, PRWaveMetadata>): HeartbeatTrigger {
        this.pWave.metadataForwarder(action)
        return this
    }

    fun qrsSubscribe(action: Consumer<QRSWaveMetadata>): HeartbeatTrigger {
        this.qrsWave.trigger(action)
        return this
    }

    fun qrsMetaProcessor(action: WaveMetadataProducer<QRSWaveMetadata>): HeartbeatTrigger {
        this.qrsWave.metadataProcessor(action)
        return this
    }

    fun qrsForward(action: BiConsumer<QRSWaveMetadata, PRWaveMetadata>): HeartbeatTrigger {
        this.qrsWave.metadataForwarder(action)
        return this
    }

    fun qSubscribe(action: Consumer<QWaveMetadata>): HeartbeatTrigger {
        this.qWave.trigger(action)
        return this
    }

    fun qMetaProcessor(action: WaveMetadataProducer<QWaveMetadata>): HeartbeatTrigger {
        this.qWave.metadataProcessor(action)
        return this
    }

    fun qForward(action: BiConsumer<QWaveMetadata, QRSWaveMetadata>): HeartbeatTrigger {
        this.qWave.metadataForwarder(action)
        return this
    }

    fun rSubscribe(action: Consumer<RWaveMetadata>): HeartbeatTrigger {
        this.rWave.trigger(action)
        return this
    }

    fun rMetaProcessor(action: WaveMetadataProducer<RWaveMetadata>): HeartbeatTrigger {
        this.rWave.metadataProcessor(action)
        return this
    }

    fun rForward(action: BiConsumer<RWaveMetadata, QRSWaveMetadata>): HeartbeatTrigger {
        this.rWave.metadataForwarder(action)
        return this
    }

    fun sSubscribe(action: Consumer<SWaveMetadata>): HeartbeatTrigger {
        this.sWave.trigger(action)
        return this
    }

    fun sMetaProcessor(action: WaveMetadataProducer<SWaveMetadata>): HeartbeatTrigger {
        this.sWave.metadataProcessor(action)
        return this
    }

    fun sForward(action: BiConsumer<SWaveMetadata, QRSWaveMetadata>): HeartbeatTrigger {
        this.sWave.metadataForwarder(action)
        return this
    }
}
