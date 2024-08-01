package com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.plt;

import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.BloodCell;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.BloodCellType;
import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.locate.BloodCellLocate;

public class Platelet extends BloodCell {
    public Platelet(BloodCellLocate locate) {
        super(locate);
    }

    @Override
    public BloodCellType type() {
        return BloodCellType.PLT;
    }
}
