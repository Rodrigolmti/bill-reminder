package com.vortex.billreminder.domain.repository

import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.model.Bill

interface BillRepository {

    suspend fun getBillList(): Result<List<Bill>>

    suspend fun addBill(bill: Bill): Result<Bill>

    suspend fun deleteBill(bill: Bill): Result<Bill>

    suspend fun updateBill(bill: Bill): Result<Bill>
}