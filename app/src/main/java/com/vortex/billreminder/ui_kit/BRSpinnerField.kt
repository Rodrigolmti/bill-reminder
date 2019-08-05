package com.vortex.billreminder.ui_kit

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.vortex.billreminder.R
import com.vortex.billreminder.util.getSizeInDp
import com.vortex.billreminder.util.safeSetTextAppearance

class BRSpinnerField : ConstraintLayout {

    private lateinit var textViewLabel: TextView
    private lateinit var spinner: Spinner
    private lateinit var textViewError: TextView

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
        setupLabel()
        setupSpinner()
        setupError()
        setupConstraints()

        attrs?.apply {
            val typedArray = context.obtainStyledAttributes(this, R.styleable.BRSpinnerField)

            label = typedArray.getString(R.styleable.BRSpinnerField_spinnerLabel)
            error = typedArray.getString(R.styleable.BRSpinnerField_spinnerError)
            prompt = typedArray.getString(R.styleable.BRSpinnerField_spinnerPrompt)

            typedArray.recycle()
        }
    }

    private fun setupLabel() {
        textViewLabel = TextView(context)
        textViewLabel.apply {
            safeSetTextAppearance(R.style.TextAppearance_Caption_Bold)
            setTextColor(
                ContextCompat.getColorStateList(
                    context,
                    R.color.color_selector_enabled_gray700_gray500
                )
            )
            id = R.id.tvBillSpinnerFieldLabel
        }
        addView(textViewLabel)
    }

    private fun setupSpinner() {
        spinner = Spinner(context)
        val params = LayoutParams(
            0,
            LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, getSizeInDp(R.dimen.margin_xxsmall), 0, 0)
        spinner.apply {
            background = ContextCompat.getDrawable(context, R.drawable.bg_bill_input_text)
            setPadding(
                getSizeInDp(R.dimen.margin_xsmall),
                getSizeInDp(R.dimen.margin_xsmall),
                getSizeInDp(R.dimen.margin_xsmall),
                getSizeInDp(R.dimen.margin_xsmall)
            )
            id = R.id.tvBillSpinnerFieldField
        }

        addView(spinner, params)
    }

    private fun setupError() {
        textViewError = TextView(context)
        val params = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, getSizeInDp(R.dimen.margin_xxsmall), 0, 0)
        textViewError.apply {
            safeSetTextAppearance(R.style.TextAppearance_Body)
            setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            id = R.id.tvBillSpinnerFieldError
            visibility = View.GONE
        }
        addView(textViewError, params)
    }

    private fun setupConstraints() {
        val set = ConstraintSet()
        set.apply {
            clone(this@BRSpinnerField)

            connect(
                textViewLabel.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START
            )
            connect(textViewLabel.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

            connect(spinner.id, ConstraintSet.TOP, textViewLabel.id, ConstraintSet.BOTTOM)
            connect(spinner.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(spinner.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

            connect(textViewError.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            connect(textViewError.id, ConstraintSet.TOP, spinner.id, ConstraintSet.BOTTOM)

            applyTo(this@BRSpinnerField)
        }
    }

    /** * Text that informs the text of the label. */
    var label: String? = null
        get() = textViewLabel.text.toString()
        set(text) {
            textViewLabel.text = text
            field = text
        }

    /** * Text that informs the text of the prompt. */
    var prompt: String? = null
        get() = spinner.prompt.toString()
        set(text) {
            spinner.prompt = text
            field = text
        }

    /** * Text that informs the error of the component. If null the text will be GONE */
    var error: String? = null
        get() = textViewError.text.toString()
        set(text) {
            textViewError.visibility =
                takeIf { text.isNullOrEmpty() }?.run { View.GONE } ?: View.VISIBLE
            textViewError.text = text
            field = text
        }
}