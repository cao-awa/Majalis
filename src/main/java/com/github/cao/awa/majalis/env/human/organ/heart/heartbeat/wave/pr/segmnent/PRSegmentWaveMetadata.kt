package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.segmnent;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class PRSegmentWaveMetadata extends WaveMetadata {
    public PRSegmentWaveMetadata() {
        super(WaveRenderShape.ABSENT);
    }

    @Override
    @NotNull
    public WaveType type() {
        return WaveType.PR_SEGMENT;
    }
}
