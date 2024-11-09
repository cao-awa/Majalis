package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.st

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape

class STSegmentWaveMetadata : WaveMetadata(WaveRenderShape.ABSENT) {
    override fun type(): WaveType = WaveType.PR_SEGMENT
}
