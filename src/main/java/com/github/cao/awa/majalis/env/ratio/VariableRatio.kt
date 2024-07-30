package com.github.cao.awa.majalis.env.ratio

class VariableRatio(amount: Long, max: Int) : Ratio(amount, max) {
    override fun copy(): Ratio = VariableRatio(super.amount(), super.max())
}
