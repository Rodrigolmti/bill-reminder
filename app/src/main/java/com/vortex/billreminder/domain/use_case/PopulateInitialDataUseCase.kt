package com.vortex.billreminder.domain.use_case

import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.repository.CategoryRepository

class PopulateInitialDataUseCase(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(): Result<Boolean> = categoryRepository.populateInitialData()
}