package com.vortex.billreminder.storage.database

import android.content.Context
import androidx.room.Room

class RoomDatabaseImpl(private val context: Context) : RoomDatabase {

    override fun database(): BillDatabase = Room.databaseBuilder(
        context.applicationContext,
        BillDatabase::class.java, "bill_database"
    ).build()
}