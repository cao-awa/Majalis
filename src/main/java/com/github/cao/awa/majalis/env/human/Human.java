package com.github.cao.awa.majalis.env.human;

import com.github.cao.awa.majalis.env.human.system.circulatory.HumanCirculatorySystem;
import com.github.cao.awa.majalis.env.human.system.immunity.HumanImmunitySystem;
import com.github.cao.awa.majalis.env.tick.Tickable;

public class Human implements Tickable {
    private final HumanImmunitySystem immunity = new HumanImmunitySystem();
    private final HumanCirculatorySystem circulatory;

    public Human(int initVolemia) {
        this.circulatory = new HumanCirculatorySystem(this, initVolemia);
        this.circulatory.initBloodCells();
    }

    public HumanCirculatorySystem circulatorySystem() {
        return this.circulatory;
    }

    @Override
    public void tick() {
        this.circulatory.tick();
        this.immunity.tick();
    }

    public static void main(String[] args) {
        Human human = new Human(6000);

        while(true) {
            human.tick();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
