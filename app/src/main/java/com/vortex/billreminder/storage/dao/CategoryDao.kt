package com.vortex.billreminder.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.vortex.billreminder.storage.model.CategoryStorage

@Dao
interface CategoryDao {

    @Insert
    fun insertCategory(categoryStorage: CategoryStorage)

    @Update
    fun updateCategory(categoryStorage: CategoryStorage)

    @Delete
    fun deleteCategory(categoryStorage: CategoryStorage)
}