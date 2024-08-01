package com.github.cao.awa.majalis.env.human.system.circulatory.blood;

import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.system.circulatory.HumanCirculatorySystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.SystemBloodCell;

public class SystemBlood {
    private final Human human;
    // Blood volume or volemia, unit is mL.
    private int volemia;

    public SystemBlood(Human human, int initVolemia)  {
        this.human = human;
        this.volemia = initVolemia;
    }

    public int volemia() {
        return this.volemia;
    }
}
