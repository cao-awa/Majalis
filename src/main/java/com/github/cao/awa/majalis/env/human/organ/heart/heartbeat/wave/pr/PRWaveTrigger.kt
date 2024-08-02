package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger

/**
 * The trigger that engine the heartbeat wave PR.
 */
class PRWaveTrigger : WaveTrigger<PRWaveMetadata, WaveMetadata>() {
    override fun primeMetadata(): PRWaveMetadata = PRWaveMetadata()
}
