package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.t;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class TWaveMetadata extends WaveMetadata {
    public TWaveMetadata() {
        super(WaveRenderShape.SMOOTHED);
    }

    @Override
    @NotNull
    public WaveType type() {
        return WaveType.P;
    }
}
