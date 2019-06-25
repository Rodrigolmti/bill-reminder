package com.vortex.billreminder.storage.dao

import androidx.room.*
import com.vortex.billreminder.storage.model.CategoryStorage

@Dao
interface CategoryDao {

    @Insert
    fun insertCategory(categoryStorage: CategoryStorage)

    @Insert
    fun insertCategories(categoryStorage: List<CategoryStorage>)

    @Update
    fun updateCategory(categoryStorage: CategoryStorage)

    @Delete
    fun deleteCategory(categoryStorage: CategoryStorage)

    @Query("SELECT * FROM category")
    fun findAll(): List<CategoryStorage>
}