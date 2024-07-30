package com.github.cao.awa.majalis.env.electrolyte;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.majalis.env.amout.molar.MolarMass;
import com.github.cao.awa.majalis.env.electrolyte.ion.Ion;
import com.github.cao.awa.majalis.env.electrolyte.ion.IonConcentration;

import java.util.Map;

public class SystemElectrolyte {
    private final Map<Ion, IonConcentration> ions = ApricotCollectionFactor.hashMap();

    public MolarMass fetchAmount(Ion ion) {
        return this.ions.getOrDefault(ion, IonConcentration.NOTHING_CONCENTRATION)
                .amount();
    }

    public SystemElectrolyte fetchAmount(IonConcentration concentration) {
        this.ions.put(
                concentration.ion(),
                concentration
        );

        return this;
    }
}
