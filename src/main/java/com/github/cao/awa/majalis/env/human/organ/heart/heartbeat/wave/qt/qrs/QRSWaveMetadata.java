package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p.PWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.q.QWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.r.RWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.s.SWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class QRSWaveMetadata extends WaveMetadata {
    private final HumanArteries arteries;
    private PRWaveMetadata prMeta;
    private QWaveMetadata qMeta;
    private RWaveMetadata rMeta;
    private SWaveMetadata sMeta;
    private int backlogOutput;
    public int peakedOutput = 0;

    public QRSWaveMetadata(HumanArteries arteries) {
        super(WaveRenderShape.COMPLEX);
        this.arteries = arteries;
    }

    public int calculateCurrentOutput(double timed) {
        if (timed >= peakedTime() || timed <= troughTime() || timed == -1) {
            return this.arteries.pumpInputMax();
        }
        int frame = peakedTime() - troughTime();
        double progress = timed - troughTime();
        return Math.min(this.arteries.pumpInputMax(), (int) ((this.arteries.pumpInputMax()) * (progress / frame)) + backlogOutput());
    }

    @Override
    @NotNull
    public WaveType type() {
        return WaveType.QRS;
    }
}
