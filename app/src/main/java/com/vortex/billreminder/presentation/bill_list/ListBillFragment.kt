package com.vortex.billreminder.presentation.bill_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.vortex.billreminder.R
import kotlinx.android.synthetic.main.list_bill_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListBillFragment : Fragment() {

    private val viewModel by viewModel<ListBillViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_bill_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fabListBillFragment.setOnClickListener { findNavController().navigate(R.id.action_listBillFragment_to_addBillFragment) }

    }
}
