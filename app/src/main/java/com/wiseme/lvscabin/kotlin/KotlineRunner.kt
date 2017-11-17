package com.wiseme.lvscabin.kotlin

import com.wiseme.lvscabin.kotlin.math.English
import com.wiseme.lvscabin.kotlin.math.Math
import com.wiseme.lvscabin.kotlin.math.primeFactorization

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/11/15.
 */
class KotlineRunner {

    fun main(args: String?) {
        testMath()
    }

    fun testMath(){
        val math = Math()
        math.apply {
            desiredScaleX
            desiredScaleX = 1
            primeFactorization(1)
            with(English()){
                subject = "English"
            }
        }
    }
}