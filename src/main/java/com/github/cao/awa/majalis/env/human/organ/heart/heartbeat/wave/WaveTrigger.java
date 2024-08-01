package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pipeline.WaveMetadataPipeline;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pipeline.WaveMetadataProducer;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class WaveTrigger<M extends WaveMetadata, L extends WaveMetadata> {
    private M metadata;
    private boolean finished = true;
    private final List<Consumer<M>> triggers = ApricotCollectionFactor.linkedList();
    private final List<BiConsumer<M, L>> forwarders = ApricotCollectionFactor.linkedList();
    private final WaveMetadataPipeline<M> pipeline = new WaveMetadataPipeline<>();

    public WaveTrigger<M, L> trigger(Consumer<M> trigger) {
        this.triggers.add(trigger);
        return this;
    }

    public List<Consumer<M>> triggers() {
        return this.triggers;
    }

    public void trigger() {
        if (this.finished) {
            M initializer = primeMetadata();
            this.metadata = initializer;
            this.pipeline.produce(initializer);

            this.finished = false;
        }

        for (Consumer<M> trigger : this.triggers) {
            trigger.accept(this.metadata);
        }
    }

    public <P extends WaveMetadata> void forwardFromTrigger(WaveTrigger<L, P> lastTrigger) {
        if (this.finished) {
            M initializer = primeMetadata();
            L lastMetadata = lastTrigger.metadata();
            this.metadata = initializer;
            this.pipeline.produce(initializer);

            for (BiConsumer<M, L> forwarder : this.forwarders) {
                forwarder.accept(this.metadata, lastMetadata);
            }

            this.finished = false;
        }

        for (Consumer<M> trigger : this.triggers) {
            trigger.accept(this.metadata);
        }
    }

    public void finish(int finishTime) {
        this.finished = true;
        this.metadata.endTime(finishTime);
    }

    public WaveRenderShape renderShape() {
        return Manipulate.supplyWhenNotNull(
                WaveMetadata::renderShape
        ).operateOrDefault(
                        metadata(),
                        WaveRenderShape.ABSENT
        );
    }

    public WaveTrigger<M, L> metadataProcessor(WaveMetadataProducer<M> producer) {
        this.pipeline.producer(producer);
        return this;
    }

    public WaveTrigger<M, L> metadataForwarder(BiConsumer<M, L> forwarder) {
        this.forwarders.add(forwarder);
        return this;
    }

    public M metadata() {
        return this.metadata;
    }

    public abstract M primeMetadata();
}

