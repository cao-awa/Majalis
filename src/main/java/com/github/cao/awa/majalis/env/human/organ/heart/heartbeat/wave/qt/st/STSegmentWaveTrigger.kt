package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.st

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qt.QTWaveMetadata

/**
 * The trigger that engine the heartbeat segment ST.
 */
class STSegmentWaveTrigger : WaveTrigger<STSegmentWaveMetadata, QTWaveMetadata>() {
    override fun primeMetadata(): STSegmentWaveMetadata = STSegmentWaveMetadata()
}
