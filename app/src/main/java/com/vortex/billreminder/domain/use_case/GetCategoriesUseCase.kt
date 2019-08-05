package com.vortex.billreminder.domain.use_case

import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.model.Category
import com.vortex.billreminder.domain.repository.CategoryRepository

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(): Result<List<Category>> {
        return categoryRepository.getCategories()
    }
}