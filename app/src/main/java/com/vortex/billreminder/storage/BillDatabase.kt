package com.vortex.billreminder.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.model.Category
import com.vortex.billreminder.storage.dao.BillDao

@Database(entities = [Bill::class, Category::class], version = 1)
abstract class BillDatabase : RoomDatabase() {

    abstract fun billDao(): BillDao

    companion object {

        private var INSTANCE: BillDatabase? = null

        fun getInstance(context: Context): BillDatabase? {
            if (INSTANCE == null) {
                synchronized(BillDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BillDatabase::class.java, "bill_reminder.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}