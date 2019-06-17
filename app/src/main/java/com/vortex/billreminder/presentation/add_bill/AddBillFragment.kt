package com.vortex.billreminder.presentation.add_bill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vortex.billreminder.R
import kotlinx.android.synthetic.main.add_bill_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddBillFragment : Fragment() {

    private val viewModel by viewModel<AddBillViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_bill_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btAddBill.setOnClickListener {
            if (validateFields()) {}
        }
    }

    private fun validateFields() : Boolean {

        var isValid = true

        bvvAddBillValue.value ?: run {
            isValid = false
        }

        bitAddBillDescription.value.takeIf { it.isNullOrEmpty() }?.let {
            bitAddBillDescription.error = "Inform a description for your bill!"
            isValid = false
        } ?: run {
            bitAddBillDescription.error = null
            isValid = true
        }

        bitAddBillDate.date?.let {
            bitAddBillDescription.error = "Inform a due date for your bill!"
            isValid = false
        } ?: run {
            bitAddBillDescription.error = null
            isValid = true
        }

        return isValid

    }
}
