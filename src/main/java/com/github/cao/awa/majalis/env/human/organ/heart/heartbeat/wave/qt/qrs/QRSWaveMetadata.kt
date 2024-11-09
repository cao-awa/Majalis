package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveType
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.q.QWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.r.RWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.s.SWaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.shape.WaveRenderShape
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries

class QRSWaveMetadata(private val arteries: HumanArteries) : WaveMetadata(WaveRenderShape.COMPLEX) {
    lateinit var prMeta: PRWaveMetadata
    lateinit var qMeta: QWaveMetadata
    lateinit var rMeta: RWaveMetadata
    lateinit var sMeta: SWaveMetadata
    var backlogOutput = 0
    var peakedOutput = 0

    fun calculateCurrentOutput(timed: Double): Int {
        if (timed == -1.0) {
            return this.arteries.pumpInputMax()
        }
        if (timed >= this.peakedTime || timed <= this.troughTime) {
            return this.arteries.pumpInputMax()
        }
        val frame = this.peakedTime - this.troughTime
        val progress = timed - this.troughTime
        return Math.min(this.arteries.pumpInputMax(), ((this.arteries.pumpInputMax()) * (progress / frame)).toInt() + this.backlogOutput)
    }

    override fun type(): WaveType = WaveType.QRS
}
