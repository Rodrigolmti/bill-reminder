package com.vortex.billreminder.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bill")
data class Bill(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "value")
    val value: Double,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "date")
    val date: Date,
    @ColumnInfo(name = "category_id")
    val categoryId: Int
)