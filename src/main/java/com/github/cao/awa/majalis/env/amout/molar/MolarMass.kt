package com.github.cao.awa.majalis.env.amout.molar

abstract class MolarMass(private var amount: Long, private var reciprocal: Int) {
    abstract fun copy(): MolarMass

    fun amount(): Long = this.amount

    open fun amount(amount: Long) = run { this.amount = amount }

    fun reciprocal(): Int = this.reciprocal

    open fun reciprocal(reciprocal: Int) = run { this.reciprocal = reciprocal }
}
