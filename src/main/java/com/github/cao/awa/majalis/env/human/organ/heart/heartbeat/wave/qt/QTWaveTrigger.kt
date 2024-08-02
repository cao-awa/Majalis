package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata

/**
 * The trigger that engine the heartbeat wave QT.
 */
class QTWaveTrigger : WaveTrigger<QTWaveMetadata, PRWaveMetadata>() {
    override fun primeMetadata(): QTWaveMetadata = QTWaveMetadata()
}
