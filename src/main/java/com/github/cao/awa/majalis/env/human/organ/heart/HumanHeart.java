package com.github.cao.awa.majalis.env.human.organ.heart;

import com.github.cao.awa.majalis.config.MajalisConfigs;
import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.HeartbeatTrigger;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.WaveMetadata;
import com.github.cao.awa.majalis.env.human.organ.heart.heartbeat.wave.qrs.QRSWaveMetadata;
import com.github.cao.awa.majalis.env.human.system.circulatory.HumanCirculatorySystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries;
import com.github.cao.awa.majalis.env.human.organ.HumanOrgan;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.veins.HumanVeins;

public class HumanHeart extends HumanOrgan {
    private final Human human;
    private final HeartbeatTrigger trigger;
    private final HumanArteries arteries;
    private final HumanVeins veins;
    private WaveMetadata state;
    private WaveMetadata subState;
    private int expectedWaiting = 400;
    // ms
    private int qrsBlood = 30;
    private int waiting = 400;
    private int leftOutput = 90000;
    private int pumpTicks = 40;

    public HumanHeart(Human humanBelong, HumanArteries arteries, HumanVeins veins) {
        this.human = humanBelong;
        this.arteries = arteries;
        this.veins = veins;
        this.trigger = new HeartbeatTrigger(this, this.arteries);

        initHeartSubscribers();
    }

    private void initHeartSubscribers() {
        this.trigger.pMetaProcessor(p -> {

        }).pSubscribe(p -> {

        }).qrsMetaProcessor(qrs ->{
            qrs.peakedOutput(90000);
            this.state = qrs;
            this.arteries.pumpInputMax(qrs.peakedOutput());
        }).qrsForward((qrs, p) -> {
            qrs.peakedTime(p.endTime() + 60);
        }).qrsSubscribe(qrs -> {

        }).qMetaProcessor(q -> {

        }).qForward((q, qrs) -> {

        }).qSubscribe(q -> {
            this.subState = q;
        });
    }

    @Override
    public void tick() {
        tickBlood();

        if (this.state != null && this.state instanceof QRSWaveMetadata qrs) {
            System.out.println(this.arteries.bloodPressure(this.trigger.times(), qrs));
        } else {
            System.out.println(this.arteries.bloodPressure(-1, null));
        }
    }

    @Override
    public void tickBlood() {
        this.trigger.tick();

        this.arteries.tick();

        this.veins.tick();
    }

    @Override
    public Human human() {
        return this.human;
    }

    @Override
    public HumanArteries arteries() {
        return this.arteries;
    }

    @Override
    public HumanVeins veins() {
        return this.veins;
    }
}
