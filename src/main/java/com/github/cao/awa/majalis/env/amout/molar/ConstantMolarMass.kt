package com.github.cao.awa.majalis.env.amout.molar

class ConstantMolarMass(amount: Long, reciprocal: Int) : MolarMass(amount, reciprocal) {
    override fun copy(): MolarMass = ConstantMolarMass(super.amount(), super.reciprocal())

    override fun amount(amount: Long) = throw UnsupportedOperationException("Set constant molar mass")

    override fun reciprocal(reciprocal: Int) = throw UnsupportedOperationException("Set constant molar mass")

    companion object {
        @JvmField
        val ZERO: MolarMass = ConstantMolarMass(0, 0)
    }
}
