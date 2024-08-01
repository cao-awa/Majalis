package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.q;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.p.PWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.QRSWaveMetadata;

/**
 * The trigger that engine the heartbeat wave P.
 */
public class QWaveTrigger extends WaveTrigger<QWaveMetadata, QRSWaveMetadata> {
    @Override
    public QWaveMetadata primeMetadata() {
        return new QWaveMetadata();
    }
}
