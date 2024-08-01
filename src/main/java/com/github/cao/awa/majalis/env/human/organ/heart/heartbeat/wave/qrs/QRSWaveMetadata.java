package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.p.PWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.q.QWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.r.RWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.s.SWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class QRSWaveMetadata extends WaveMetadata {
    private PWaveMetadata pMeta;
    private QWaveMetadata qMeta;
    private RWaveMetadata rMeta;
    private SWaveMetadata sMeta;
    private final HumanArteries arteries;
    private final WaveRenderShape renderShape = WaveRenderShape.COMPLEX;
    public int peakedOutput = 0;

    public QRSWaveMetadata(HumanArteries arteries) {
        this.arteries = arteries;
    }

    public int calculateCurrentOutput(double timed) {
        if (timed >= peakedTime() || timed == -1) {
            return this.arteries.pumpInputMax();
        }
        int frame = peakedTime() - troughTime();
        double progress = timed - troughTime();
        return (int) (this.arteries.pumpInputMax() * (progress / frame));
    }

    @Override
    public WaveRenderShape renderShape() {
        return this.renderShape;
    }

    @Override
    public WaveType type() {
        return WaveType.QRS;
    }
}
