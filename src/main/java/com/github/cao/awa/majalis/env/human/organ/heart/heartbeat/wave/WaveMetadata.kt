package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class WaveMetadata {
    @NotNull
    private final WaveRenderShape renderShape;
    private int peakedTime = 0;
    private int troughTime = 0;
    private int endTime = 0;

    public WaveMetadata(@NotNull WaveRenderShape renderShape) {
        this.renderShape = renderShape;
    }

    @NotNull
    public abstract WaveType type();
}
