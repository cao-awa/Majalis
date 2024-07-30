package com.github.cao.awa.majalis.env.ratio

class ConstantRatio(amount: Long, max: Int) : Ratio(amount, max) {
    override fun copy(): Ratio = ConstantRatio(super.amount(), super.max())

    override fun amount(amount: Long) = throw UnsupportedOperationException("Set constant ratio")

    override fun max(max: Int) = throw UnsupportedOperationException("Set constant ratio")

    companion object {
        @JvmField
        val ZERO: Ratio = ConstantRatio(0, 0)
    }
}
