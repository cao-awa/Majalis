package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.segmnent;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata;

/**
 * The trigger that engine the heartbeat wave P.
 */
public class PRSegmentWaveTrigger extends WaveTrigger<PRSegmentWaveMetadata, PRWaveMetadata> {
    @Override
    public PRSegmentWaveMetadata primeMetadata() {
        return new PRSegmentWaveMetadata();
    }
}
