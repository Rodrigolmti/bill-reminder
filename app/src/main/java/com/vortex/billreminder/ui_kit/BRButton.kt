package com.vortex.billreminder.ui_kit

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.Button

class BRButton : Button {

    constructor(context: Context) : super(context) {
        setupView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setupView(attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        setupView(attrs)
    }

    private fun setupView(attrs: AttributeSet? = null) {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            48
        )
        text = "dfsd"
    }
}