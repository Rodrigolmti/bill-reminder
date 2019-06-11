package com.vortex.billreminder.presentation.bill_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vortex.billreminder.R

class ListBillFragment : Fragment() {

    companion object {
        fun newInstance() = ListBillFragment()
    }

    private lateinit var viewModel: ListBillViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_bill_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListBillViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
