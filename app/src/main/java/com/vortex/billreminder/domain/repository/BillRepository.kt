package com.vortex.billreminder.domain.repository

import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.util.Result

interface BillRepository {

    suspend fun getBillList(): Result<List<Bill>>

    suspend fun addBill(bill: Bill): Result<Bill>

    suspend fun deleteBill(bill: Bill): Result<Bill>

    suspend fun updateBill(bill: Bill): Result<Bill>
}