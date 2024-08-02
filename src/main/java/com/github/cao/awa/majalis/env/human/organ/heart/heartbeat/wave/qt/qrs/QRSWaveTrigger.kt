package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.QTWaveMetadata
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries

/**
 * The trigger that engine the heartbeat wave QRS.
 */
class QRSWaveTrigger(private val arteries: HumanArteries) : WaveTrigger<QRSWaveMetadata, QTWaveMetadata>() {
    override fun primeMetadata(): QRSWaveMetadata = QRSWaveMetadata(this.arteries)
}
