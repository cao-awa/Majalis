package com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.system.circulatory.HumanCirculatorySystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.HumanVascular;

import java.util.List;
import java.util.Random;

public class HumanArteries extends HumanVascular {
    private final List<HumanArteries> connected = ApricotCollectionFactor.arrayList();
    private final Human human;
    private final Random random = new Random();

    public HumanArteries(Human humanBelong, HumanCirculatorySystem circulatorySystem, int initVolemia) {
        super(initVolemia, 1500, 1000);
        this.human = humanBelong;
    }
}
