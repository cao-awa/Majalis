package com.github.cao.awa.majalis.env.human.system.circulatory.blood;

import com.github.cao.awa.majalis.config.MajalisConfigs;
import com.github.cao.awa.majalis.env.human.system.HumanSystem;

public abstract class HumanVascular extends HumanSystem {

    // Cardiac output(when arteries) or vascular output(when veins), unit is μL/time.
    private int bloodInput = 0;
    // Blood volume or volemia, unit is mL.
    private int volemia;
    private int expectedShrinkage;
    private int expectedElasticity;
    // Shrinkage pressure or peripheral resistance, unit is mmHg/(μL/time).
    private int shrinkage;
    // Vascular elasticity or vascular compliance, unit is μL/mmHg.
    private int elasticity;
    private int pumpTicks = 0;

    public HumanVascular(int initVolemia, int expectedShrinkage, int expectedElasticity) {
        this.volemia = initVolemia;
        this.expectedShrinkage = expectedShrinkage;
        this.expectedElasticity = expectedElasticity;
        this.shrinkage = expectedShrinkage;
        this.elasticity = expectedElasticity;
    }

    /**
     * <p>
     * Blood pressure calculate method:
     * <blockquote><pre>
     *  P = R(<sup><sup>CO</sup>&frasl<sub>e<sup>V</sup></sub></sup>&frasl<sub>C</sub>(1-e<sup>-RC</sup>))
     *
     * </pre></blockquote><p>
     * Where P is blood pressure, CO is cardiac output.
     * <p>
     * Where R is peripheral resistant, C is vascular compliance.
     *
     * @author 草
     * @author 草二号机
     *
     * @since 1.0.0
     *
     * @return The blood pressure united by mmHg
     */
    public int bloodPressure() {
        // Compliance also known as ’C’ unit is mL.
        double compliance = this.elasticity / 1000D;
        // Resistant also known as ’R’ unit is mL.
        double resistant = this.shrinkage / 1000D;
        // Input also known as ’CO’ unit is mL.
        double input = this.bloodInput / 1000D;
        // Volemia also known as ’V’ unit is mL.
        double volemia = this.volemia;
        double offset = 1 - Math.exp(- (resistant * compliance));
        double volemiaOffset =  (1 - Math.exp(-resistant));
        double basePressure = volemia * volemiaOffset * (1 - Math.exp(- resistant)) / 3.896695;
        double pumpOffset = 1 - Math.exp(-compliance);
        double pumpPressure = ((input * pumpOffset) * offset) / 1.896695;

        return (int) (
                basePressure + pumpPressure
        );
    }

    public void cardiacOutput(int input) {
        this.bloodInput += input;
    }

    public static void main(String[] args) {
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

        if (this.bloodInput > 0) {
            this.bloodInput -= MajalisConfigs.expectedTickTime * 268;
        } else {
            this.pumpTicks = 0;
        }
    }
}
