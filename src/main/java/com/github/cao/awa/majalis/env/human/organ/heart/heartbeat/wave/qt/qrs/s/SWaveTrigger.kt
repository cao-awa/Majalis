package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.s

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.QRSWaveMetadata

/**
 * The trigger that engine the heartbeat wave S.
 */
class SWaveTrigger : WaveTrigger<SWaveMetadata, QRSWaveMetadata>() {
    override fun primeMetadata(): SWaveMetadata = SWaveMetadata()
}
