package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.p

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata

/**
 * The trigger that engine the heartbeat wave P.
 */
class PWaveTrigger : WaveTrigger<PWaveMetadata, PRWaveMetadata>() {
    override fun primeMetadata(): PWaveMetadata = PWaveMetadata()
}
