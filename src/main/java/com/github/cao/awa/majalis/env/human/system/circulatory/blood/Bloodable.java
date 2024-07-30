package com.github.cao.awa.majalis.env.human.system.circulatory.blood;

import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.veins.HumanVeins;
import com.github.cao.awa.majalis.env.tick.Tickable;

public interface Bloodable extends Tickable {
    HumanArteries arteries();
    HumanVeins veins();
    void tickBlood();
}
