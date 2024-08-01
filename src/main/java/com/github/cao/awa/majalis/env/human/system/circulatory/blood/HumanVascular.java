package com.github.cao.awa.majalis.env.human.system.circulatory.blood;

import com.github.cao.awa.majalis.config.MajalisConfigs;
import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.system.HumanSystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.SystemBloodCell;

public abstract class HumanVascular extends HumanSystem {
    private final Human human;
    // Cardiac output(when arteries) or vascular output(when veins), unit is μL/time.
    private int pumpInputMax = 0;
    private int expectedShrinkage;
    private int expectedElasticity;
    // Shrinkage pressure or peripheral resistance, unit is mmHg/(μL/time).
    private int shrinkage;
    // Vascular elasticity or vascular compliance, unit is μL/mmHg.
    private int elasticity;

    public HumanVascular(Human human, int expectedShrinkage, int expectedElasticity) {
        this.human = human;
        this.expectedShrinkage = expectedShrinkage;
        this.expectedElasticity = expectedElasticity;
        this.shrinkage = expectedShrinkage;
        this.elasticity = expectedElasticity;
    }

    public int elasticity() {
        return this.elasticity;
    }

    public int shrinkage() {
        return this.shrinkage;
    }

    public int volemia() {
        return (int) (this.human.circulatorySystem().volemia() * baseVolemiaRate());
    }

    public double liftVolemia() {
        return this.human.circulatorySystem().volemia() * baseVolemiaRate();
    }

    public void pumpInputMax(int input) {
        this.pumpInputMax = input;
    }

    public int pumpInputMax() {
        return this.pumpInputMax;
    }

    public void increaseTension(int shrinkage) {
        this.shrinkage += shrinkage;
    }

    public void increaseTension(int shrinkage, int elasticity) {
        increaseTension(shrinkage);
        decreaseCompliance(elasticity);
    }

    public void decreaseTension(int shrinkage) {
        this.shrinkage -= shrinkage;
        this.shrinkage = Math.max(0, this.shrinkage);
    }

    public void decreaseTension(int shrinkage, int elasticity) {
        decreaseTension(shrinkage);
        increaseCompliance(elasticity);
    }

    public void decreaseCompliance(int elasticity) {
        this.elasticity -= elasticity;
        this.elasticity = Math.max(0, this.elasticity);
    }

    public void increaseCompliance(int elasticity) {
        this.elasticity += elasticity;
    }

    public void tickBloodForward() {

    }

    public HumanVascular tickElasticity() {
        return this;
    }

    public HumanVascular tickShrinkage() {
        return this;
    }

    public HumanVascular tickBloodFlow() {
        return this;
    }

    @Override
    public void tick() {
        if (this.shrinkage > this.expectedShrinkage) {
            decreaseTension(MajalisConfigs.expectedTickTime);
        } else {
            increaseTension(MajalisConfigs.expectedTickTime);
        }

        if (this.elasticity < this.expectedElasticity) {
            increaseCompliance(MajalisConfigs.expectedTickTime * 14);
        } else {
            decreaseCompliance(MajalisConfigs.expectedTickTime * 14);
        }

        if (this.pumpInputMax > 0) {
            this.pumpInputMax -= MajalisConfigs.expectedTickTime * 268;
        }
    }

    public abstract double baseVolemiaRate();
}
