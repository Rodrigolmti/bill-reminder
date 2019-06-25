package com.vortex.billreminder.ui_kit

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.vortex.billreminder.R
import com.vortex.billreminder.util.MoneyMaskWatcher
import com.vortex.billreminder.util.brlCurrency
import com.vortex.billreminder.util.safeSetTextAppearance

class BRValueView : ConstraintLayout {

    private lateinit var textViewLabel: TextView
    private lateinit var editTextValue: EditText
    private lateinit var divisor: View

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

    override fun isEnabled() = textViewLabel.isEnabled && editTextValue.isEnabled

    override fun setEnabled(enabled: Boolean) {
        textViewLabel.isEnabled = enabled
        editTextValue.isEnabled = enabled
    }

    private fun setupView(attrs: AttributeSet? = null) {

        layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )

        setupLabel()
        setupEditText()
        setupDivisor()
        setupConstraints()
        editTextValue.addTextChangedListener(MoneyMaskWatcher.getBRFormatter(editTextValue))

        attrs?.apply {
            val typedArray = context.obtainStyledAttributes(this, R.styleable.BRValueView)

            label = typedArray.getString(R.styleable.BRValueView_valueLabel)
            hint = typedArray.getString(R.styleable.BRValueView_valueHint)

            typedArray.recycle()
        }
    }

    private fun setupLabel() {
        textViewLabel = TextView(context)
        textViewLabel.apply {
            text = context.getString(R.string.general_currency)
            safeSetTextAppearance(R.style.Currency)
            setTextColor(ContextCompat.getColorStateList(context, R.color.color_selector_enabled_gray700_gray500))
            id = R.id.tvBillValueLabel
        }
        addView(textViewLabel)
    }

    private fun setupEditText() {
        editTextValue = EditText(context)
        editTextValue.apply {
            filters = arrayOf<InputFilter>(InputFilter.LengthFilter(10))
            safeSetTextAppearance(R.style.Currency_Value)
            inputType = InputType.TYPE_CLASS_NUMBER
            id = R.id.tvBillValueEditText
            maxLines = 1
            hint = "0,00"
        }
        editTextValue.background = null
        addView(editTextValue)
    }

    private fun setupDivisor() {
        val params = LayoutParams(
            0,
            2
        )
        divisor = View(context)
        divisor.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.gray500))
            id = R.id.tvBillValueDivisor
        }
        addView(divisor, params)
    }

    private fun setupConstraints() {
        val set = ConstraintSet()
        set.apply {
            clone(this@BRValueView)

            connect(textViewLabel.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
            connect(textViewLabel.id, ConstraintSet.END, editTextValue.id, ConstraintSet.START)
            connect(textViewLabel.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(textViewLabel.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

            connect(editTextValue.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
            connect(editTextValue.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            connect(editTextValue.id, ConstraintSet.START, textViewLabel.id, ConstraintSet.END)
            connect(editTextValue.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

            createHorizontalChain(
                ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID, ConstraintSet.LEFT,
                intArrayOf(textViewLabel.id, editTextValue.id),
                null,
                ConstraintSet.CHAIN_PACKED
            )

            connect(divisor.id, ConstraintSet.START, textViewLabel.id, ConstraintSet.START)
            connect(divisor.id, ConstraintSet.END, editTextValue.id, ConstraintSet.END)
            connect(divisor.id, ConstraintSet.TOP, editTextValue.id, ConstraintSet.BOTTOM)

            applyTo(this@BRValueView)
        }
    }

    /** * Text that informs the field of the label. */
    var label: String? = null
        get() = textViewLabel.text.toString()
        set(text) {
            takeIf { !text.isNullOrEmpty() }?.run { textViewLabel.text = text }
            field = text
        }

    /** * Return the informed value in Double . */
    var value: Double? = null
        get() = MoneyMaskWatcher.doubleValue(editTextValue.text.toString())
        set(text) {
            editTextValue.setText(value?.brlCurrency)
            field = text
        }

    /** * Text that informs the hint of the label. */
    var hint: String? = null
        get() = editTextValue.text.toString()
        set(text) {
            takeIf { !text.isNullOrEmpty() }?.run { editTextValue.hint = text }
            field = text
        }
}