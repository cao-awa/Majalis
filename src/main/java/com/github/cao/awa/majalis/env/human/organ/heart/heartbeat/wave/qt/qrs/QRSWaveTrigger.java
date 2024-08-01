package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p.PWaveMetadata;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries;

/**
 * The trigger that engine the heartbeat wave P.
 */
public class QRSWaveTrigger extends WaveTrigger<QRSWaveMetadata, PRWaveMetadata> {
    private final HumanArteries arteries;

    public QRSWaveTrigger(HumanArteries arteries) {
        this.arteries = arteries;
    }

    @Override
    public QRSWaveMetadata primeMetadata() {
        return new QRSWaveMetadata(this.arteries);
    }
}