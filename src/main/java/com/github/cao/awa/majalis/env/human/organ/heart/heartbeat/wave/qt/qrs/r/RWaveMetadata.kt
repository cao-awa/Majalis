package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.r

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape

class RWaveMetadata : WaveMetadata(WaveRenderShape.PEAKED) {
    override fun type(): WaveType = WaveType.R
}
