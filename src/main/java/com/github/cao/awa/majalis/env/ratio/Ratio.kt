package com.github.cao.awa.majalis.env.ratio

abstract class Ratio(private var amount: Long, private var max: Int) {
    abstract fun copy(): Ratio

    fun amount(): Long = this.amount

    open fun amount(amount: Long) = run { this.amount = amount }

    fun max(): Int = this.max

    open fun max(max: Int) = run { this.max = max }

    open fun transform(value: Long): Long = value * (this.max / this.amount)
}
