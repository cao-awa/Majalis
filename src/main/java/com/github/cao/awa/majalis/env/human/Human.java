package com.github.cao.awa.majalis.env.human;

import com.github.cao.awa.majalis.env.human.system.circulatory.HumanCirculatorySystem;
import com.github.cao.awa.majalis.env.human.system.immunity.HumanImmunitySystem;
import com.github.cao.awa.majalis.env.tick.Tickable;

public class Human implements Tickable {
    private final HumanImmunitySystem immunity = new HumanImmunitySystem();
    private final HumanCirculatorySystem circulatory = new HumanCirculatorySystem(this);

    @Override
    public void tick() {
        this.circulatory.tick();
        this.immunity.tick();
    }
}
