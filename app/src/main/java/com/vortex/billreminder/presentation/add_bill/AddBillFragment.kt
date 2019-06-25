package com.vortex.billreminder.presentation.add_bill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vortex.billreminder.R
import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.use_case.AddBillUseCase
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
            val bill = Bill(
                value = bvvAddBillValue.value,
                description = bitAddBillDescription.value
            )
            controlFieldInteraction(false)
            viewModel.addBill(bill)
        }

        viewModel.addBillLiveData.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            it.contentIfNotHandled?.let { failure ->
                controlFieldInteraction(true)
                when (failure) {
                    is AddBillUseCase.AddBillFailure -> {
                        if (failure.errors.contains(AddBillUseCase.AddBillFailure.ErrorType.INVALID_VALUE)) {

                        }
                        if (failure.errors.contains(AddBillUseCase.AddBillFailure.ErrorType.INVALID_DESCRIPTION)) {
                            bitAddBillDescription.error = getString(R.string.add_bill_description_error)
                        }
                    }
                }
            }
        })
    }

    private fun controlFieldInteraction(enabled: Boolean) {
        bitAddBillDescription.isEnabled = enabled
        bitAddBillCategory.isEnabled = enabled
        bvvAddBillValue.isEnabled = enabled
        bitAddBillDate.isEnabled = enabled
        btAddBill.isEnabled = enabled
    }
}
