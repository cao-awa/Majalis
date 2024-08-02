package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.t

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.QTWaveMetadata

/**
 * The trigger that engine the heartbeat wave T.
 */
class TWaveTrigger : WaveTrigger<TWaveMetadata, QTWaveMetadata>() {
    override fun primeMetadata(): TWaveMetadata = TWaveMetadata()
}
