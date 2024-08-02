package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.q;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.QRSWaveMetadata;

/**
 * The trigger that engine the heartbeat wave P.
 */
public class QWaveTrigger extends WaveTrigger<QWaveMetadata, QRSWaveMetadata> {
    @Override
    public QWaveMetadata primeMetadata() {
        return new QWaveMetadata();
    }
}
