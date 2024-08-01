package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.p;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger;

/**
 * The trigger that engine the heartbeat wave P.
 */
public class PWaveTrigger extends WaveTrigger<PWaveMetadata, WaveMetadata> {
    @Override
    public PWaveMetadata primeMetadata() {
        return new PWaveMetadata();
    }
}
