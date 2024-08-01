package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pipeline;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;

import java.security.Principal;
import java.util.List;

public final class WaveMetadataPipeline<M extends WaveMetadata> {
    private final List<WaveMetadataProducer<M>> producers = ApricotCollectionFactor.linkedList();
    private boolean interrupted = false;

    public void producer(WaveMetadataProducer<M> producer) {
        this.producers.add(producer);
    }

    public M produce(M initializer) {
        for (WaveMetadataProducer<M> producer : this.producers) {
            if (this.interrupted) {
                break;
            }

            producer.process(initializer);
        }

        return initializer;
    }

    public WaveMetadataPipeline<M> interrupt() {
        this.interrupted = true;
        return this;
    }
}
