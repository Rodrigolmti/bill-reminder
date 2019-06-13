package com.vortex.billreminder.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.model.Category
import com.vortex.billreminder.storage.dao.BillDao

@Database(entities = [Bill::class, Category::class], version = 1)
@TypeConverters(Converters::class)
abstract class BillDatabase : RoomDatabase() {
    abstract fun billDao(): BillDao
}