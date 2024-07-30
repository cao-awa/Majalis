package com.github.cao.awa.majalis.env.human.organ.heart;

import com.github.cao.awa.majalis.config.MajalisConfigs;
import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.system.circulatory.HumanCirculatorySystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries;
import com.github.cao.awa.majalis.env.human.organ.HumanOrgan;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.veins.HumanVeins;

public class HumanHeart extends HumanOrgan {
    private final Human human;
    private final HumanCirculatorySystem circulatorySystem;
    private final HumanArteries arteries;
    private final HumanVeins veins;
    // ms
    private int qrsBlood = 30;
    private int waiting = 300;
    private int leftOutput = 90000;
    private int pumpTicks = 40;

    public HumanHeart(Human humanBelong, HumanCirculatorySystem circulatorySystem, HumanArteries arteries, HumanVeins veins) {
        this.human = humanBelong;
        this.circulatorySystem = circulatorySystem;
        this.arteries = arteries;
        this.veins = veins;
    }

    @Override
    public void tick() {
        System.out.println(arteries().bloodPressure());

        tickBlood();
    }

    @Override
    public void tickBlood() {
        tickLeftVentricle();
    }

    private void tickLeftVentricle() {
        this.arteries.tick();
        this.veins.tick();

        if (this.waiting > 0 && this.qrsBlood < 1) {
            this.waiting -= MajalisConfigs.expectedTickTime;

            if (this.waiting > 280 && this.waiting < 295) {
                pumpBlood();
            } else {
                this.leftOutput = 15000;
                this.pumpTicks = 15;
            }
            return;
        } else {
            this.waiting = 400;
        }

        if (this.qrsBlood < 1) {
            this.qrsBlood = 40;
            this.pumpTicks = 40;
            System.out.println("- Reset heart beat");
            this.leftOutput = 90000;
        }

        this.qrsBlood -= MajalisConfigs.expectedTickTime;

        pumpBlood();
    }

    public void pumpBlood() {
        if (this.leftOutput > 0 && this.pumpTicks > 0) {
            int output = this.leftOutput / this.pumpTicks;
            this.arteries.cardiacOutput(output);
            this.leftOutput -= output;
            this.pumpTicks -= MajalisConfigs.expectedTickTime;
        }
    }

    public int bloodPressure() {
        return this.arteries.bloodPressure();
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
