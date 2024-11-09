package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pipeline.WaveMetadataPipeline
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pipeline.WaveMetadataProducer
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.manipulate.ManipulateBuilder
import com.github.cao.awa.sinuatum.manipulate.QuickManipulate
import java.util.function.BiConsumer
import java.util.function.Consumer

abstract class WaveTrigger<M : WaveMetadata, L : WaveMetadata> {
    private lateinit var metadata: M
    private var finished = true
    private val triggers: MutableList<Consumer<M>> = ApricotCollectionFactor.linkedList()
    private val finishingTriggers: MutableList<Consumer<M>> = ApricotCollectionFactor.linkedList()
    private val forwarders: MutableList<BiConsumer<M, L>> = ApricotCollectionFactor.linkedList()
    private val pipeline = WaveMetadataPipeline<M>()

    fun trigger(trigger: Consumer<M>, finishingTrigger: Consumer<M>): WaveTrigger<M, L> {
        this.triggers.add(trigger)
        this.finishingTriggers.add(finishingTrigger)
        return this
    }

    fun triggers(): List<Consumer<M>> = this.triggers

    fun trigger() {
        if (this.finished) {
            val initializer = primeMetadata()
            this.metadata = initializer
            this.pipeline.produce(initializer)

            this.finished = false
        }

        for (trigger in this.triggers) {
            trigger.accept(this.metadata)
        }
    }

    fun <P : WaveMetadata> forwardFromTrigger(lastTrigger: WaveTrigger<L, P>) {
        if (this.finished) {
            val initializer = primeMetadata()
            val lastMetadata = lastTrigger.metadata()
            this.metadata = initializer
            this.pipeline.produce(initializer)

            for (forwarder in this.forwarders) {
                forwarder.accept(this.metadata, lastMetadata)
            }

            this.finished = false
        }

        for (trigger in this.triggers) {
            trigger.accept(this.metadata)
        }
    }

    fun finish(finishTime: Int) {
        if (this.finished) {
            return
        }
        this.finished = true
        this.metadata.endTime = finishTime

        for (trigger in this.finishingTriggers) {
            trigger.accept(this.metadata)
        }
    }

    fun renderShape(): WaveRenderShape {
        return ManipulateBuilder.supplyWhenNotNull(WaveMetadata::renderShape).operateOrDefault(
            metadata(),
            WaveRenderShape.ABSENT
        )
    }

    fun metadataProcessor(producer: WaveMetadataProducer<M>): WaveTrigger<M, L> {
        this.pipeline.producer(producer)
        return this
    }

    fun metadataForwarder(forwarder: BiConsumer<M, L>): WaveTrigger<M, L> {
        this.forwarders.add(forwarder)
        return this
    }

    fun metadata(): M = this.metadata

    abstract fun primeMetadata(): M
}

