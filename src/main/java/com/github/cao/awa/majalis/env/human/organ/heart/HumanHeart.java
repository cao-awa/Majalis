package com.github.cao.awa.majalis.env.human.organ.heart;

import com.github.cao.awa.majalis.config.MajalisConfigs;
import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.system.circulatory.HumanCirculatorySystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.HumanBloodSystem;
import com.github.cao.awa.majalis.env.human.organ.HumanOrgan;

public class HumanHeart extends HumanOrgan {
    private final Human human;
    private final HumanCirculatorySystem circulatorySystem;
    private final HumanBloodSystem arteries;
    // ms
    private int qrsBlood = 30;
    private int waiting = 300;

    public HumanHeart(Human humanBelong, HumanCirculatorySystem circulatorySystem) {
        this.human = humanBelong;
        this.circulatorySystem = circulatorySystem;
        this.arteries = new HumanBloodSystem(humanBelong, circulatorySystem);
    }

    @Override
    public void tick() {
        this.arteries.cardiacOutput(90);

        tickLeftVentricle();
    }

    private void tickLeftVentricle() {
        this.arteries.tick();

        if (this.waiting > 0 && this.qrsBlood < 1) {
            this.waiting -= MajalisConfigs.expectedTickTime;

            if (this.waiting > 230 && this.waiting < 245) {
                int expectedTension = MajalisConfigs.expectedTickTime * 148;
                int expectedCompliance = MajalisConfigs.expectedTickTime * 22;
                this.arteries.increaseTension(expectedTension, expectedCompliance);
            }
            return;
        } else {
            this.waiting = 400;
        }

        if (this.qrsBlood < 1) {
            this.qrsBlood = 40;
            System.out.println("- Reset heart beat");
        }

        this.qrsBlood -= MajalisConfigs.expectedTickTime;

        int expectedTension = MajalisConfigs.expectedTickTime * 192;
        int expectedCompliance = MajalisConfigs.expectedTickTime * 28;
        this.arteries.increaseTension(expectedTension, expectedCompliance);
    }

    public int bloodPressure() {
        return this.arteries.bloodPressure();
    }

    @Override
    public Human human() {
        return this.human;
    }
}
