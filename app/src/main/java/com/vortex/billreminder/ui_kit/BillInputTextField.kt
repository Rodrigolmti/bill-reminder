package com.vortex.billreminder.ui_kit

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.vortex.billreminder.R
import com.vortex.billreminder.util.getSizeInDp
import com.vortex.billreminder.util.safeSetTextAppearance

class BillInputTextField : ConstraintLayout {

    private val textViewLabel: TextView by lazy { TextView(context) }
    private val editText: EditText by lazy { EditText(context) }
    private val textViewError: TextView by lazy { TextView(context) }

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
        setupEditText()
        setupError()
        setupConstraints()

        attrs?.apply {
            val typedArray = context.obtainStyledAttributes(this, R.styleable.BillInputTextField)

            label = typedArray.getString(R.styleable.BillInputTextField_inputLabel)
            hint = typedArray.getString(R.styleable.BillInputTextField_inputHint)
            error = typedArray.getString(R.styleable.BillInputTextField_inputError)

            typedArray.recycle()
        }
    }

    private fun setupLabel() {
        textViewLabel.apply {
            safeSetTextAppearance(R.style.TextAppearance_Caption_Bold)
            id = R.id.tvBillInputTextFieldLabel
        }
        addView(textViewLabel)
    }

    private fun setupEditText() {
        val params = LayoutParams(
            0,
            LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, getSizeInDp(R.dimen.margin_xxsmall), 0, 0)
        editText.apply {
            background = ContextCompat.getDrawable(context, R.drawable.bg_bill_input_text)
            setPadding(
                getSizeInDp(R.dimen.margin_xsmall),
                getSizeInDp(R.dimen.margin_xsmall),
                getSizeInDp(R.dimen.margin_xsmall),
                getSizeInDp(R.dimen.margin_xsmall)
            )
            id = R.id.etBillInputTextFieldField
        }
        addView(editText, params)
    }

    private fun setupError() {
        val params = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, getSizeInDp(R.dimen.margin_xxsmall), 0, 0)
        textViewError.apply {
            safeSetTextAppearance(R.style.TextAppearance_Body)
            setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            id = R.id.tvBillInputTextFieldError
            visibility = View.GONE
        }
        addView(textViewError, params)
    }

    private fun setupConstraints() {
        val set = ConstraintSet()
        set.apply {
            clone(this@BillInputTextField)

            connect(textViewLabel.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(textViewLabel.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

            connect(editText.id, ConstraintSet.TOP, textViewLabel.id, ConstraintSet.BOTTOM)
            connect(editText.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(editText.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

            connect(textViewError.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            connect(textViewError.id, ConstraintSet.TOP, editText.id, ConstraintSet.BOTTOM)

            applyTo(this@BillInputTextField)
        }
    }

    var label: String? = null
        get() = textViewLabel.text.toString()
        set(text) {
            textViewLabel.text = text
            field = text
        }

    var hint: String? = null
        get() = editText.hint.toString()
        set(text) {
            editText.hint = text
            field = text
        }

    val value: String?
        get() = editText.text.toString()

    var error: String? = null
        get() = textViewError.text.toString()
        set(text) {
            textViewError.visibility = takeIf { text.isNullOrEmpty() }?.run { View.GONE } ?: View.VISIBLE
            textViewError.text = text
            field = text
        }
}