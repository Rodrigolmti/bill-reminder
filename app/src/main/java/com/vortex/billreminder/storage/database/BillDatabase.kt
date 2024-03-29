package com.vortex.billreminder.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vortex.billreminder.storage.dao.BillDao
import com.vortex.billreminder.storage.dao.CategoryDao
import com.vortex.billreminder.storage.model.BillStorage
import com.vortex.billreminder.storage.model.CategoryStorage

@Database(entities = [BillStorage::class, CategoryStorage::class], version = 1)
@TypeConverters(Converters::class)
abstract class BillDatabase : RoomDatabase() {
    abstract fun billDao(): BillDao
    abstract fun categoryDao(): CategoryDao
}