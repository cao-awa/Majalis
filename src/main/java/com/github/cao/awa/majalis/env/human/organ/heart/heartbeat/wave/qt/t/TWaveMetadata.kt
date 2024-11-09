package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.t

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape

class TWaveMetadata : WaveMetadata(WaveRenderShape.SMOOTHED) {
    override fun type(): WaveType = WaveType.P
}
