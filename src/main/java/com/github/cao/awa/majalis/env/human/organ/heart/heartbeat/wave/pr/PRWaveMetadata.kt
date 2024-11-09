package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p.PWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.segmnent.PRSegmentWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class PRWaveMetadata extends WaveMetadata {
    private PWaveMetadata pMeta;
    private PRSegmentWaveMetadata prSegmentMeta;

    public PRWaveMetadata() {
        super(WaveRenderShape.COMPLEX);
    }

    @Override
    @NotNull
    public WaveType type() {
        return WaveType.PR_INTERVAL;
    }
}
