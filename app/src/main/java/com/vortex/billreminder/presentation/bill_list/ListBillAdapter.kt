package com.vortex.billreminder.presentation.bill_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vortex.billreminder.R
import com.vortex.billreminder.domain.model.Bill
import kotlinx.android.synthetic.main.item_bill.view.*

class ListBillAdapter : RecyclerView.Adapter<ListBillAdapter.ViewHolder>() {

    private val values: MutableList<Bill> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bill,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    fun updateListOfBills(bills: List<Bill>) {
        values.clear()
        values.addAll(bills)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Bill) {
            itemView.tvItemBillName.text = item.description
            itemView.tvItemBillValue.text = item.value.toString()
            itemView.tvItemBillDate.text = item.date.toString()
        }
    }
}