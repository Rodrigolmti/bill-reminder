package com.vortex.billreminder.presentation.add_bill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
            viewModel.addBill(bill)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            it.contentIfNotHandled?.let { failure ->
                when(failure) {
                    is AddBillUseCase.AddBillFailure -> {
                        failure.errors.forEach { error ->
                            when(error) {
                                AddBillUseCase.AddBillFailure.ErrorType.INVALID_VALUE -> {

                                }
                                AddBillUseCase.AddBillFailure.ErrorType.INVALID_DESCRIPTION -> {

                                }
                            }
                        }
                    }
                }
            }
        })
    }
}
