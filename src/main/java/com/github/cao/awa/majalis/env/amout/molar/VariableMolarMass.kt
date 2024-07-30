package com.github.cao.awa.majalis.env.amout.molar

class VariableMolarMass(amount: Long, reciprocal: Int) : MolarMass(amount, reciprocal) {
    override fun copy(): MolarMass = VariableMolarMass(super.amount(), super.reciprocal())
}
