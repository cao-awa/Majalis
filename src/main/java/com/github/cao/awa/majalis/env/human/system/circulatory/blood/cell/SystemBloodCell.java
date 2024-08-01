package com.github.cao.awa.majalis.env.human.system.circulatory.blood.cell;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SystemBloodCell {
    private final Map<BloodCellType, List<BloodCellConcentration>> cells = ApricotCollectionFactor.hashMap();

    public List<BloodCellConcentration> fetchCells(BloodCellType cellType) {
        return this.cells.getOrDefault(cellType, Collections.emptyList());
    }

    public SystemBloodCell fetchCell(BloodCellConcentration concentration) {
        this.cells.computeIfAbsent(
                concentration.cell().type(),
                key -> ApricotCollectionFactor.arrayList()
        );

        this.cells.get(concentration.cell().type()).add(concentration);

        return this;
    }
}
