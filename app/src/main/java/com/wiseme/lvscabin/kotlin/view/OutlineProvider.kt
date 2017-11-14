package com.wiseme.lvscabin.kotlin.view

import android.graphics.Outline
import android.graphics.Rect
import android.view.View
import android.view.ViewOutlineProvider

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/11/14.
 */
class OutlineProvider(val rect: Rect, var scaleX: Float, var scaleY: Float, var yShift: Int) : ViewOutlineProvider() {

    override fun getOutline(view: View?, outline: Outline?) {
    }
}