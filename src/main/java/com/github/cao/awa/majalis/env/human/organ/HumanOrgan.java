package com.github.cao.awa.majalis.env.human.organ;

import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.Bloodable;
import com.github.cao.awa.majalis.env.tick.Tickable;

public abstract class HumanOrgan implements Bloodable {
    public abstract Human human();
}
