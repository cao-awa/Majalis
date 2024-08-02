package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.QRSWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.q.QWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.r.RWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.s.SWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.st.STSegmentWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.t.TWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class QTWaveMetadata extends WaveMetadata {
    private PRWaveMetadata prMeta;
    private QRSWaveMetadata qrsMeta;
    private STSegmentWaveMetadata stMeta;
    private TWaveMetadata tMeta;

    public QTWaveMetadata() {
        super(WaveRenderShape.COMPLEX);
    }

    @Override
    @NotNull
    public WaveType type() {
        return WaveType.QT;
    }
}
