package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata;

/**
 * The trigger that engine the heartbeat wave P.
 */
public class PWaveTrigger extends WaveTrigger<PWaveMetadata, PRWaveMetadata> {
    @Override
    public PWaveMetadata primeMetadata() {
        return new PWaveMetadata();
    }
}
