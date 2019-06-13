package com.vortex.billreminder.util

import android.util.TypedValue
import android.view.View
import android.os.Build
import android.widget.TextView


fun View.getSizeInDp(metric: Int): Int {
    val resources = context.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        getResources().getDimension(metric),
        resources.displayMetrics
    ).toInt()
}

fun TextView.safeSetTextAppearance(resId: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        setTextAppearance(context, resId)
    } else {
        setTextAppearance(resId)
    }
}