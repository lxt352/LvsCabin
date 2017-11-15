package com.wiseme.lvscabin.kotlin.math

import android.util.Log

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/11/14.
 */
class Math {
    fun print() {
        primeFactorization(10)
    }
}

fun Math.primeFactorization(number: Int) {
    var n = 0
    when (number) {
        1 -> n = 1
        2 -> n = 2
    }
    Log.i("TAG", "n = " + n)
}