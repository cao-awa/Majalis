package com.github.cao.awa.majalis.env.human.organ.heart

import com.github.cao.awa.majalis.env.human.Human
import com.github.cao.awa.majalis.env.human.organ.HumanOrgan
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.HeartbeatTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p.PWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.QTWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.QRSWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.q.QWaveMetadata
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.veins.HumanVeins

class HumanHeart(private val human: Human, private val arteries: HumanArteries, private val veins: HumanVeins) : HumanOrgan() {
    private val trigger = HeartbeatTrigger(this, this.arteries)
    private val state: WaveMetadata? = null
    private var subState: WaveMetadata? = null
    private val pumpInRightAtria = 0
    private val pumpInRightVentricle = 0
    private val pumpInLeftAtria = 0
    private val pumpInLeftVentricle = 0

    init {
        initHeartSubscribers()
    }

    private fun initHeartSubscribers() {
        this.trigger.pMetaProcessor { p ->

        }.pSubscribe { p ->

        }.qrsMetaProcessor { qrs ->
            qrs.backlogOutput = this.arteries.pumpInputMax()
            qrs.peakedOutput = 90000
            this.subState = qrs
            this.arteries.pumpInputMax(qrs.peakedOutput)
        }.qrsForward { qrs, qt ->
            qrs.peakedTime = qt.endTime + 60
        }.qrsSubscribe { qrs ->

        }.qMetaProcessor { q ->

        }.qForward { q, qrs ->

        }.qSubscribe { q ->
            this.subState = q
        }
    }

    override fun tick() {
        tickBlood()

        if (this.subState != null && this.subState is QRSWaveMetadata) {
            println(this.arteries.bloodPressure(this.trigger.times(), this.subState as QRSWaveMetadata))
        } else {
            println(this.arteries.bloodPressure(-1, null))
        }
    }

    override fun tickBlood() {
        tickBloodPump()
        tickBloodCell()
    }

    private fun tickBloodPump() {
        this.trigger.finished { trigger -> trigger.resetHeartbeat() }

        this.trigger.tick()

        this.arteries.tick()

        this.veins.tick()
    }

    private fun tickBloodCell() {
    }

    override fun human(): Human {
        return this.human
    }

    override fun arteries(): HumanArteries {
        return this.arteries
    }

    override fun veins(): HumanVeins {
        return this.veins
    }
}
