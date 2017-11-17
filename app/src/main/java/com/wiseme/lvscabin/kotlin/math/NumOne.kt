package com.wiseme.lvscabin.kotlin.math

import android.util.Log

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/11/14.
 */
class Math {
    var desiredScaleX: Int? = null
        get() {
            return field
        }
        set(value) {
            value?.let {
                this.desiredScaleX = 1
                field = value
            }
        }

    fun print() {
        primeFactorization(10)
    }
}

class English {

    var subject: String? = null

}

fun Math.primeFactorization(number: Int) {
    var n = 0
    when (number) {
        1 -> n = 1
        2 -> n = 2
    }
    Log.i("TAG", "n = " + n)
}