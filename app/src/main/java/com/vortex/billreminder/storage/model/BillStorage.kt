package com.vortex.billreminder.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bill")
class BillStorage (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "category_id")
    val categoryId: Int? = null,
    val value: Double?,
    val description: String?,
    val date: Date = Date()
)