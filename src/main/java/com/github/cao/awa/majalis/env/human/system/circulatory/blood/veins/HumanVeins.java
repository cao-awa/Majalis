package com.github.cao.awa.majalis.env.human.system.circulatory.blood.veins;

import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.system.circulatory.HumanCirculatorySystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.HumanVascular;

public class HumanVeins extends HumanVascular {
    private final Human human;

    public HumanVeins(Human humanBelong) {
        super(humanBelong, 85, 17000);
        this.human = humanBelong;
    }

    @Override
    public double baseVolemiaRate() {
        return 0.6;
    }
}
