package com.github.cao.awa.majalis.env.human.system.circulatory.blood;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.majalis.config.MajalisConfigs;
import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.organ.heart.HumanHeart;
import com.github.cao.awa.majalis.env.human.system.HumanSystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.HumanCirculatorySystem;
import com.github.cao.awa.majalis.env.tick.Tickable;

import java.util.List;
import java.util.Random;

public class HumanBloodSystem extends HumanSystem {
    private final List<HumanBloodSystem> connected = ApricotCollectionFactor.arrayList();
    private final Human human;
    private final Random random = new Random();
    // Cardiac output, unit is mL/time.
    private int cardiacOutput = 100;
    // Blood volume or volemia, unit is mL.
    private int volemia = 0;
    private int expectedShrinkage = 19000;
    private int expectedElasticity = 15000;
    // Shrinkage pressure or peripheral resistance, unit is mmHg/(μL/time).
    private int shrinkage = 19000;
    // Vascular elasticity or vascular compliance, unit is μL/mmHg.
    private int elasticity = 15000;

    public HumanBloodSystem(Human humanBelong, HumanCirculatorySystem circulatorySystem) {
        this.human = humanBelong;
    }

    /**
     * <p>
     * Blood pressure calculate method:
     * <blockquote><pre>
     *  P = <sup>CO</sup>&frasl<sub>C</sub>(1-e<sup>-RC</sup>)
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
        return (int) (
                (
                        // Convert ‘CO’ and ’C’ to L.
                        (this.cardiacOutput / 1000D) / (this.elasticity / 1000000D)
                ) *
                        (
                                1 - Math.exp(
                                        - (
                                                // Convert ’R’ to mL.
                                                (this.shrinkage / 1000D) *
                                                        // Convert ’C’ to L.
                                                        (this.elasticity / 1000000D))
                                )
                        )
        );
    }

    public void cardiacOutput(int input) {
        this.cardiacOutput = input * 60;
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

    public HumanBloodSystem tickElasticity() {
        return this;
    }

    public HumanBloodSystem tickShrinkage() {
        return this;
    }

    public HumanBloodSystem tickBloodFlow() {
        return this;
    }

    @Override
    public void tick() {
        if (this.shrinkage > 19000) {
            decreaseTension(MajalisConfigs.expectedTickTime * 22);
        } else {
            increaseTension(MajalisConfigs.expectedTickTime * 22);
        }

        if (this.elasticity < 15000) {
            increaseCompliance(MajalisConfigs.expectedTickTime * 12);
        } else {
            decreaseCompliance(MajalisConfigs.expectedTickTime * 12);
        }
    }
}
