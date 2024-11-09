package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p.PWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.segmnent.PRSegmentWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape

class PRWaveMetadata : WaveMetadata(WaveRenderShape.COMPLEX) {
    lateinit var pMeta: PWaveMetadata
    lateinit var prSegmentMeta: PRSegmentWaveMetadata

    override fun type(): WaveType {
        return WaveType.PR_INTERVAL
    }
}
