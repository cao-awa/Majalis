package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class PWaveMetadata extends WaveMetadata {
    public PWaveMetadata() {
        super(WaveRenderShape.SMOOTHED);
    }

    @Override
    @NotNull
    public WaveType type() {
        return WaveType.P;
    }
}
