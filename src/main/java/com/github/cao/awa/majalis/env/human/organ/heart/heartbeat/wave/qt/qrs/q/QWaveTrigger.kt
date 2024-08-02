package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.q

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.QRSWaveMetadata

/**
 * The trigger that engine the heartbeat wave Q.
 */
class QWaveTrigger : WaveTrigger<QWaveMetadata, QRSWaveMetadata>() {
    override fun primeMetadata(): QWaveMetadata = QWaveMetadata()
}
