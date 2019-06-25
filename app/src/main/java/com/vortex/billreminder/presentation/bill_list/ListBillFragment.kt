package com.vortex.billreminder.presentation.bill_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vortex.billreminder.R
import kotlinx.android.synthetic.main.list_bill_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListBillFragment : Fragment() {

    private val viewModel by viewModel<ListBillViewModel>()
    private lateinit var billAdapter: ListBillAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_bill_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycle.addObserver(viewModel)
        setupAdapter()

        fabListBillFragment.setOnClickListener { findNavController().navigate(R.id.action_listBillFragment_to_addBillFragment) }
        viewModel.billListLiveDate.observe(viewLifecycleOwner, Observer { billAdapter.updateListOfBills(it) })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun setupAdapter() {
        billAdapter = ListBillAdapter()
        rvListBillFragment.layoutManager = LinearLayoutManager(context)
        rvListBillFragment.adapter = billAdapter
    }
}
