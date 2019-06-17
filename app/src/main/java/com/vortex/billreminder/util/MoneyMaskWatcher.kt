package com.vortex.billreminder.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class MoneyMaskWatcher(text: EditText, locale: Locale) : TextWatcher {

    private val mText: WeakReference<EditText> = WeakReference(text)
    private val mFormatter: NumberFormat = NumberFormat.getCurrencyInstance(locale)
    private var mIsUpdating: Boolean = false

    override fun afterTextChanged(s: Editable) {}

    override fun beforeTextChanged(
        s: CharSequence, start: Int, count: Int,
        after: Int
    ) {
        mText.get()?.setSelection(mText.get()?.text?.length ?: 0)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        var aux = s
        if (mIsUpdating) {
            mIsUpdating = false
            return
        }
        mIsUpdating = true
        val str = unmask(aux.toString())

        try {
            val parsed = java.lang.Double.parseDouble(str)
            if (parsed == 0.toDouble()) {
                mText.get()?.text = null
                return
            }
            aux = mFormatter.format(parsed / 100)
            /* Don't remove this line */
            if (mText.get()?.text?.toString()?.contentEquals(aux) == false) {
                mText.get()?.setText(aux)
            }
        } catch (e: NumberFormatException) {
        }

        mText.get()?.setSelection(mText.get()?.text?.length ?: 0)
    }

    companion object {

        fun getBRFormatter(currencyView: EditText): MoneyMaskWatcher {
            val mask = MoneyMaskWatcher(currencyView, Locale.getDefault())
            val formatter = mask.mFormatter as DecimalFormat
            val symbols = formatter.decimalFormatSymbols
            symbols.currencySymbol = ""
            formatter.decimalFormatSymbols = symbols
            return mask
        }

        fun unmask(masked: String?) = masked?.replace("[^\\d]".toRegex(), "") ?: ""

        fun doubleValue(masked: String): Double = try {
            java.lang.Double.parseDouble(unmask(masked)) / 100
        } catch (ignore: NumberFormatException) {
            0.0
        }
    }
}
