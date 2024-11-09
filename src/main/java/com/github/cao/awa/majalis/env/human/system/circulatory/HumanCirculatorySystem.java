package com.github.cao.awa.majalis.env.human.system.circulatory;

import com.github.cao.awa.majalis.env.human.Human;
import com.github.cao.awa.majalis.env.human.organ.heart.HumanHeart;
import com.github.cao.awa.majalis.env.human.system.HumanSystem;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.SystemBlood;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.arteries.HumanArteries;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.BloodCellConcentration;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.SystemBloodCell;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.locate.BloodCellLocate;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.plt.Platelet;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.rbc.RedBloodCell;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.veins.HumanVeins;

public class HumanCirculatorySystem extends HumanSystem {
    private final Human human;
    private final SystemBlood blood;
    private final SystemBloodCell bloodCells = new SystemBloodCell();
    private final HumanHeart heart;

    public HumanCirculatorySystem(Human humanBelong, int initVolemia) {
        this.human = humanBelong;
        this.heart = new HumanHeart(
                humanBelong,
                new HumanArteries(humanBelong),
                new HumanVeins(humanBelong)
        );
        this.blood = new SystemBlood(humanBelong, initVolemia);
    }

    public void initBloodCells() {
        this.bloodCells.fetchCell(new BloodCellConcentration(new RedBloodCell(BloodCellLocate.ARTERIES, 99),
                (long) (heart().arteries().liftVolemia() * Math.pow(10, 13) * 2.5)
        ));

        this.bloodCells.fetchCell(new BloodCellConcentration(new RedBloodCell(BloodCellLocate.VEINS, 60),
                (long) (heart().veins().liftVolemia() * Math.pow(10, 13) * 2.5)
        ));

        this.bloodCells.fetchCell(new BloodCellConcentration(new Platelet(BloodCellLocate.ARTERIES),
                (long) (heart().arteries().liftVolemia() * Math.pow(10, 9) * 260)
        ));

        this.bloodCells.fetchCell(new BloodCellConcentration(new Platelet(BloodCellLocate.VEINS),
                (long) (heart().veins().liftVolemia() * Math.pow(10, 9) * 260)
        ));
    }

    public int volemia() {
        return this.blood.volemia();
    }

    public SystemBloodCell bloodCells() {
        return this.bloodCells;
    }

    public HumanHeart heart() {
        return this.heart;
    }

    @Override
    public void tick() {
        this.heart.tick();
    }
}
