package com.vortex.billreminder.domain.use_case

import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.repository.CategoryRepository

class CheckInitialDataUseCase(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(): Result<Boolean> = categoryRepository.checkInitialData()
}