package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.r;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class RWaveMetadata extends WaveMetadata {
    public RWaveMetadata() {
        super(WaveRenderShape.PEAKED);
    }

    @Override
    @NotNull
    public WaveType type() {
        return WaveType.R;
    }
}
