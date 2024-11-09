package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.s

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape

class SWaveMetadata : WaveMetadata(WaveRenderShape.INVERTED) {
    override fun type(): WaveType = WaveType.S
}
