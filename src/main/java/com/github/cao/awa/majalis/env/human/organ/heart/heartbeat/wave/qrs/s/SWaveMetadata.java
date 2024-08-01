package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.s;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class SWaveMetadata extends WaveMetadata {
    private WaveRenderShape renderShape = WaveRenderShape.INVERTED;

    @Override
    public WaveRenderShape renderShape() {
        return this.renderShape;
    }

    @Override
    public WaveType type() {
        return WaveType.S;
    }
}
