package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape

abstract class WaveMetadata(val renderShape: WaveRenderShape) {
    var peakedTime = 0
    var troughTime = 0
    var endTime = 0

    abstract fun type(): WaveType
}
