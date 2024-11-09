package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.u;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.t.TWaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class UWaveMetadata extends WaveMetadata {
    private TWaveMetadata tMeta;

    public UWaveMetadata() {
        super(WaveRenderShape.SMOOTHED);
    }

    @Override
    @NotNull
    public WaveType type() {
        return WaveType.U;
    }
}
