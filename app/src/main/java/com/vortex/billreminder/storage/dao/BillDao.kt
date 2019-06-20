package com.vortex.billreminder.storage.dao

import androidx.room.*
import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.storage.model.BillStorage

@Dao
interface BillDao {

    @Insert
    fun insert(bill: BillStorage)

    @Update
    fun update(bill: BillStorage)

    @Delete
    fun delete(bill: BillStorage)

    @Query("SELECT * FROM bill")
    fun findAll() : List<BillStorage>

}