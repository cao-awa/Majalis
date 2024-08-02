package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.r

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.qrs.QRSWaveMetadata

/**
 * The trigger that engine the heartbeat wave R.
 */
class RWaveTrigger : WaveTrigger<RWaveMetadata, QRSWaveMetadata>() {
    override fun primeMetadata(): RWaveMetadata = RWaveMetadata()
}
