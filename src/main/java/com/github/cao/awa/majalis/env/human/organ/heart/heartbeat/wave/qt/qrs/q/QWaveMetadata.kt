package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.q

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape

class QWaveMetadata : WaveMetadata(WaveRenderShape.INVERTED) {
    override fun type(): WaveType = WaveType.Q
}
