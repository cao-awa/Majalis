package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.segmnent;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class PRSegmentWaveMetadata extends WaveMetadata {
    private WaveRenderShape renderShape = WaveRenderShape.ABSENT;

    @Override
    public WaveRenderShape renderShape() {
        return this.renderShape;
    }

    @Override
    public WaveType type() {
        return WaveType.PR_SEGMENT;
    }
}
