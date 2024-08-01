package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p.PWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.segmnent.PRSegmentWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class PRWaveMetadata extends WaveMetadata {
    private PWaveMetadata pMeta;
    private PRSegmentWaveMetadata prSegmentMeta;
    private WaveRenderShape renderShape = WaveRenderShape.COMPLEX;

    @Override
    public WaveRenderShape renderShape() {
        return this.renderShape;
    }

    @Override
    public WaveType type() {
        return WaveType.PR_INTERVAL;
    }
}
