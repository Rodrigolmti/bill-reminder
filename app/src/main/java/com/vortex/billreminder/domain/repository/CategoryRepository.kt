package com.vortex.billreminder.domain.repository

import com.vortex.billreminder.domain.Result

interface CategoryRepository {

    suspend fun checkInitialData(): Result<Boolean>

    suspend fun populateInitialData(): Result<Boolean>

}