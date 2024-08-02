package com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.segmnent

import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveTrigger
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.pr.PRWaveMetadata

/**
 * The trigger that engine the heartbeat segment PR.
 */
class PRSegmentWaveTrigger : WaveTrigger<PRSegmentWaveMetadata, PRWaveMetadata>() {
    override fun primeMetadata(): PRSegmentWaveMetadata = PRSegmentWaveMetadata()
}
