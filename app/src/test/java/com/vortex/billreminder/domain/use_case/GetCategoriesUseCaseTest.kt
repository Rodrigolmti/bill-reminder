package com.vortex.billreminder.domain.use_case

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.vortex.billreminder.domain.Failure
import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.model.Category
import com.vortex.billreminder.domain.repository.CategoryRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetCategoriesUseCaseTest {

    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private val categoryRepository: CategoryRepository = mockk()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCategoriesUseCase = GetCategoriesUseCase(categoryRepository)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun `validate when category repository has success`() = runBlocking {

        val category = mockk<Category> {
            every { id } returns 0
            every { name } returns "Taxes"
            every { iconName } returns "taxes_icon"
        }

        val listOfCategories = listOf(category, category, category)
        coEvery { categoryRepository.getCategories() } returns Result.Success(listOfCategories)
        val response = getCategoriesUseCase.invoke()
        assertThat(response).isInstanceOf(Result.Success::class)

        if (response is Result.Success) {
            assertk.assertThat(response.data).all {
                contains(category)
                hasSize(3)
            }
        }
    }

    @Test
    fun `validate when category repository has error`() = runBlocking {

        val category = mockk<Category> {
            every { id } returns 0
            every { name } returns "Taxes"
            every { iconName } returns "taxes_icon"
        }

        val serviceError = Failure.ServiceError("message")

        coEvery { categoryRepository.getCategories() } returns Result.Error(serviceError)
        val response = getCategoriesUseCase.invoke()

        assertk.assertThat(response).isInstanceOf(Result.Error::class)

        if (response is Result.Error) {
            assertk.assertThat(response.failure).isInstanceOf(Failure.ServiceError::class)
            val responseError = response.failure as Failure.ServiceError
            assertThat(responseError).all {
                isEqualTo(serviceError)
            }
        }
    }
}