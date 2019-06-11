package com.vortex.billreminder.presentation.add_bill

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vortex.billreminder.R

class AddBillFragment : Fragment() {

    companion object {
        fun newInstance() = AddBillFragment()
    }

    private lateinit var viewModel: AddBillViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_bill_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddBillViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
