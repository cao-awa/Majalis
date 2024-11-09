package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.u

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.t.TWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape

class UWaveMetadata : WaveMetadata(WaveRenderShape.SMOOTHED) {
    lateinit var tMeta: TWaveMetadata

    override fun type(): WaveType {
        return WaveType.U
    }
}
