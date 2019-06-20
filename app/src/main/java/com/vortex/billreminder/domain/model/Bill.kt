package com.vortex.billreminder.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bill")
data class Bill(
    val value: Double?,
    val description: String?,
    val date: Date = Date()
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    @ColumnInfo(name = "category_id")
    val categoryId: Int = 0
}