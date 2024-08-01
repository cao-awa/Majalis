package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pipeline;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;

public interface WaveMetadataProducer<M extends WaveMetadata> {
    void process(M metadata);
}
