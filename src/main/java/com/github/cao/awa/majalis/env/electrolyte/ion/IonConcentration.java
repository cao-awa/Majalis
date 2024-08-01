package com.github.cao.awa.majalis.env.electrolyte.ion;

import com.github.cao.awa.majalis.env.amout.molar.ConstantMolarMass;
import com.github.cao.awa.majalis.env.amout.molar.MolarMass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public record IonConcentration(Ion ion, MolarMass amount) {
    public static final IonConcentration NOTHING_CONCENTRATION = new IonConcentration(Ion.NOTHING, ConstantMolarMass.ZERO);
}
