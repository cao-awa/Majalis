package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave;

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape;

public abstract class WaveMetadata {
    private int peakedTime = 0;
    private int troughTime = 0;
    private int endTime = 0;

    public final void peakedTime(int peekedTime) {
        this.peakedTime = peekedTime;
    }

    public final int peakedTime() {
        return this.peakedTime;
    }

    public final void troughTime(int troughTime) {
        this.troughTime = troughTime;
    }

    public final int troughTime() {
        return this.troughTime;
    }

    public final void endTime(int endTime) {
        this.endTime = endTime;
    }

    public final int endTime() {
        return this.endTime;
    }

    public abstract WaveRenderShape renderShape();
    public abstract WaveType type();
}
