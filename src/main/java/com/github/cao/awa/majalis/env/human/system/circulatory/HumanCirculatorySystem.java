package com.github.cao.awa.majalis.env.human.system.circulatory;

import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.organ.heart.HumanHeart;
import com.github.cao.awa.majalis.env.human.system.HumanSystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.veins.HumanVeins;

public class HumanCirculatorySystem extends HumanSystem {
    private final Human human;
    private final HumanHeart heart;

    public HumanCirculatorySystem(Human humanBelong, int initVolemia) {
        this.human = humanBelong;
        this.heart = new HumanHeart(
                humanBelong,
                this,
                new HumanArteries(
                        humanBelong,
                        this,
                        (int)(initVolemia * 0.1)
                ),
                new HumanVeins(
                        humanBelong,
                        this,
                        (int)(initVolemia * 0.6)
                )
        );
    }

    @Override
    public void tick() {
        this.heart.tick();
    }
}
