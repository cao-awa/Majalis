package com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell;

import com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell.locate.BloodCellLocate;

import java.sql.JDBCType;

public abstract class BloodCell {
    private final BloodCellLocate locate;

    public BloodCell(BloodCellLocate locate) {
        this.locate = locate;
    }

    public abstract BloodCellType type();

    public BloodCellLocate locate() {
        return this.locate;
    }
}
