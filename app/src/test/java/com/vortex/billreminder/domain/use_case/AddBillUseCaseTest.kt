package com.vortex.billreminder.domain.use_case

import assertk.all
import assertk.assertThat
import assertk.assertions.*
import com.vortex.billreminder.domain.Failure
import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.repository.BillRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class AddBillUseCaseTest {

    private lateinit var addBillUseCase: AddBillUseCase
    private val billRepository: BillRepository = mockk()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        addBillUseCase = AddBillUseCase(billRepository)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun `validate when bill repository has success`() = runBlocking {

        val bill = mockk<Bill> {
            every { value } returns 10.0
            every { description } returns "Energy bill"
            every { date } returns Date()
        }

        coEvery { billRepository.addBill(any()) } returns Result.Success(bill)
        val response = addBillUseCase.invoke(bill)
        assertk.assertThat(response).isInstanceOf(Result.Success::class)

        if (response is Result.Success) {
            assertThat(response.data).all {
                isEqualTo(bill)
            }
        }
    }

    @Test
    fun `validate when bill repository has error`() = runBlocking {

        val bill = mockk<Bill> {
            every { value } returns 10.0
            every { description } returns "Energy bill"
            every { date } returns Date()
        }

        val serviceError = Failure.ServiceError("message")

        coEvery { billRepository.addBill(any()) } returns Result.Error(serviceError)
        val response = addBillUseCase.invoke(bill)

        assertk.assertThat(response).isInstanceOf(Result.Error::class)

        if (response is Result.Error) {
            assertk.assertThat(response.failure).isInstanceOf(Failure.ServiceError::class)
            val responseError = response.failure as Failure.ServiceError
            assertThat(responseError).all {
                isEqualTo(serviceError)
            }
        }
    }

    @Test
    fun `validate when bill has empty value`() = runBlocking {

        val response = addBillUseCase.invoke(Bill(0.0, "Description"))
        assertk.assertThat(response).isInstanceOf(Result.Error::class)

        if (response is Result.Error) {
            assertk.assertThat(response.failure).isInstanceOf(AddBillUseCase.AddBillFailure::class)
            val responseError = response.failure as AddBillUseCase.AddBillFailure
            assertk.assertThat(responseError.errors).all {
                contains(AddBillUseCase.AddBillFailure.ErrorType.INVALID_VALUE)
                hasSize(1)
            }
        }
    }

    @Test
    fun `validate when bill has empty description`() = runBlocking {

        val response = addBillUseCase.invoke(Bill(35.0, ""))
        assertk.assertThat(response).isInstanceOf(Result.Error::class)

        if (response is Result.Error) {
            assertk.assertThat(response.failure).isInstanceOf(AddBillUseCase.AddBillFailure::class)
            val responseError = response.failure as AddBillUseCase.AddBillFailure
            assertk.assertThat(responseError.errors).all {
                contains(AddBillUseCase.AddBillFailure.ErrorType.INVALID_DESCRIPTION)
                hasSize(1)
            }
        }
    }

    @Test
    fun `validate when both params are empty`() = runBlocking {

        val response = addBillUseCase.invoke(Bill(0.0, ""))
        assertk.assertThat(response).isInstanceOf(Result.Error::class)

        if (response is Result.Error) {
            assertk.assertThat(response.failure).isInstanceOf(AddBillUseCase.AddBillFailure::class)
            val responseError = response.failure as AddBillUseCase.AddBillFailure
            assertk.assertThat(responseError.errors).all {
                contains(AddBillUseCase.AddBillFailure.ErrorType.INVALID_VALUE)
                contains(AddBillUseCase.AddBillFailure.ErrorType.INVALID_DESCRIPTION)
                hasSize(2)
            }
        }
    }
}