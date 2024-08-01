package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.s;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.QRSWaveMetadata;

/**
 * The trigger that engine the heartbeat wave P.
 */
public class SWaveTrigger extends WaveTrigger<SWaveMetadata, QRSWaveMetadata> {
    @Override
    public SWaveMetadata primeMetadata() {
        return new SWaveMetadata();
    }
}
