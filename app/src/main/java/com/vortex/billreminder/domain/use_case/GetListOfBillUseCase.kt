package com.vortex.billreminder.domain.use_case

import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.repository.BillRepository
import com.vortex.billreminder.domain.Result
import java.util.*

class GetListOfBillUseCase(
    private val billRepository: BillRepository
) {

    suspend operator fun invoke(): Result<List<Bill>> {

        val mock = listOf(
            Bill(25.0, "Life insurance", Date()),
            Bill(35.0, "Life bill", Date()),
            Bill( 45.0, "Bill insurance", Date())
        )

        return Result.Success(mock)
    }
}