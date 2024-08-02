package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.u

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.t.TWaveMetadata

/**
 * The trigger that engine the heartbeat wave U.
 */
class UWaveTrigger : WaveTrigger<UWaveMetadata, TWaveMetadata>() {
    override fun primeMetadata(): UWaveMetadata = UWaveMetadata()
}
