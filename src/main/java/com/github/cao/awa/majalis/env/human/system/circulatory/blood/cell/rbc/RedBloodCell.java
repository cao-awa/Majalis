package com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.rbc;

import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.BloodCell;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.BloodCellConcentration;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.BloodCellType;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.locate.BloodCellLocate;

public class RedBloodCell extends BloodCell {
    private int oxygenation;

    public RedBloodCell(BloodCellLocate locate, int oxygenation) {
        super(locate);
        updateOxygenation(oxygenation);
    }

    public void updateOxygenation(int oxygenation) {
        this.oxygenation = Math.min(100, oxygenation);
    }

    public int oxygenation() {
        return this.oxygenation;
    }

    @Override
    public BloodCellType type() {
        return BloodCellType.RBC;
    }
}
