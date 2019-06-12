package com.vortex.billreminder.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.vortex.billreminder.domain.model.Bill

@Dao
interface BillDao {

    @Insert
    fun insertBill(bill: Bill)

    @Update
    fun updateBill(bill: Bill)

    @Delete
    fun deleteBill(bill: Bill)
}