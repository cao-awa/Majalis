package com.github.cao.awa.majalis.env.human.system.circulatory;

import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.organ.heart.HumanHeart;
import com.github.cao.awa.majalis.env.human.system.HumanSystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.HumanBloodSystem;
import com.github.cao.awa.majalis.env.tick.Tickable;

public class HumanCirculatorySystem extends HumanSystem {
    private final Human human;
    private final HumanHeart heart;

    public HumanCirculatorySystem(Human humanBelong) {
        this.human = humanBelong;
        this.heart = new HumanHeart(humanBelong, this);
    }

    @Override
    public void tick() {
        this.heart.tick();
    }
}
