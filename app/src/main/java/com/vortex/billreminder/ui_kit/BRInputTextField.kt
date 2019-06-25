package com.vortex.billreminder.ui_kit

import android.app.DatePickerDialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.vortex.billreminder.R
import com.vortex.billreminder.util.getSizeInDp
import com.vortex.billreminder.util.safeSetTextAppearance
import com.vortex.billreminder.util.toFormattedString
import java.util.*

class BRInputTextField : ConstraintLayout {

    private lateinit var textViewLabel: TextView
    private lateinit var editText: EditText
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

    override fun isEnabled(): Boolean {
        return textViewLabel.isEnabled && editText.isEnabled
    }

    override fun setEnabled(enabled: Boolean) {
        textViewLabel.isEnabled = enabled
        editText.isEnabled = enabled
    }

    private fun setupView(attrs: AttributeSet? = null) {
        setupLabel()
        setupEditText()
        setupError()
        setupConstraints()

        attrs?.apply {
            val typedArray = context.obtainStyledAttributes(this, R.styleable.BRInputTextField)

            label = typedArray.getString(R.styleable.BRInputTextField_inputLabel)
            hint = typedArray.getString(R.styleable.BRInputTextField_inputHint)
            error = typedArray.getString(R.styleable.BRInputTextField_inputError)
            when (typedArray.getInt(R.styleable.BRInputTextField_inputType, 0)) {
                1 -> {
                    setupCalendar()
                }
            }

            typedArray.recycle()
        }
    }

    private fun setupLabel() {
        textViewLabel = TextView(context)
        textViewLabel.apply {
            safeSetTextAppearance(R.style.TextAppearance_Caption_Bold)
            setTextColor(ContextCompat.getColorStateList(context, R.color.color_selector_enabled_gray700_gray500))
            id = R.id.tvBillInputTextFieldLabel
        }
        addView(textViewLabel)
    }

    private fun setupEditText() {
        editText = EditText(context)
        val params = LayoutParams(
            0,
            LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, getSizeInDp(R.dimen.margin_xxsmall), 0, 0)
        editText.apply {
            setHintTextColor(ContextCompat.getColorStateList(context, R.color.color_selector_enabled_gray700_gray500))
            setTextColor(ContextCompat.getColorStateList(context, R.color.color_selector_enabled_gray700_gray500))
            background = ContextCompat.getDrawable(context, R.drawable.bg_bill_input_text)
            setPadding(
                getSizeInDp(R.dimen.margin_xsmall),
                getSizeInDp(R.dimen.margin_xsmall),
                getSizeInDp(R.dimen.margin_xsmall),
                getSizeInDp(R.dimen.margin_xsmall)
            )
            imeOptions = EditorInfo.IME_ACTION_NEXT
            id = R.id.etBillInputTextFieldField
            maxLines = 1
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                error = null
            }
        })

        addView(editText, params)
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
            id = R.id.tvBillInputTextFieldError
            visibility = View.GONE
        }
        addView(textViewError, params)
    }

    private fun setupConstraints() {
        val set = ConstraintSet()
        set.apply {
            clone(this@BRInputTextField)

            connect(textViewLabel.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(textViewLabel.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

            connect(editText.id, ConstraintSet.TOP, textViewLabel.id, ConstraintSet.BOTTOM)
            connect(editText.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(editText.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

            connect(textViewError.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            connect(textViewError.id, ConstraintSet.TOP, editText.id, ConstraintSet.BOTTOM)

            applyTo(this@BRInputTextField)
        }
    }

    private fun setupCalendar() {
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, R.drawable.ic_calendar), null)
        editText.isFocusable = false
        editText.isClickable = true

        editText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val selectedCalendar = Calendar.getInstance().apply { set(year, month, dayOfMonth) }
                    editText.setText(selectedCalendar.time.toFormattedString())
                    date = selectedCalendar.time
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }

    /** * Text that informs the text of the label. */
    var label: String? = null
        get() = textViewLabel.text.toString()
        set(text) {
            textViewLabel.text = text
            field = text
        }

    /** * Return the date selected by the user. */
    var date: Date? = null

    /** * Text that informs the hint label. */
    var hint: String? = null
        get() = editText.hint.toString()
        set(text) {
            editText.hint = text
            field = text
        }

    /** * Return the value of the field in string. */
    val value: String?
        get() = editText.text.toString()

    /** * Text that informs the error of the component. If null the text will be GONE */
    var error: String? = null
        get() = textViewError.text.toString()
        set(text) {
            textViewError.visibility = takeIf { text.isNullOrEmpty() }?.run { View.GONE } ?: View.VISIBLE
            textViewError.text = text
            field = text
        }
}