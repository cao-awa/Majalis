package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger;

/**
 * The trigger that engine the heartbeat wave P.
 */
public class PRWaveTrigger extends WaveTrigger<PRWaveMetadata, WaveMetadata> {
    @Override
    public PRWaveMetadata primeMetadata() {
        return new PRWaveMetadata();
    }
}
