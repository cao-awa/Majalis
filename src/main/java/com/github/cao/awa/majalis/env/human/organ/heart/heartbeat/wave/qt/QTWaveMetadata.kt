package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.QRSWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.st.STSegmentWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.t.TWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape

class QTWaveMetadata : WaveMetadata(WaveRenderShape.COMPLEX) {
    lateinit var prMeta: PRWaveMetadata
    lateinit var qrsMeta: QRSWaveMetadata
    lateinit var stMeta: STSegmentWaveMetadata
    lateinit var tMeta: TWaveMetadata

    override fun type(): WaveType {
        return WaveType.QT
    }
}
