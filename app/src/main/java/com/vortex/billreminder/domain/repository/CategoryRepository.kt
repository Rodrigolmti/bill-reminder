package com.vortex.billreminder.domain.repository

import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.model.Category

interface CategoryRepository {

    suspend fun checkInitialData(): Result<Boolean>

    suspend fun populateInitialData(): Result<Boolean>

    suspend fun getCategories(): Result<List<Category>>

}