package com.vortex.billreminder.domain.use_case

import com.vortex.billreminder.domain.Failure
import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.repository.BillRepository

class AddBillUseCase(
    private val billRepository: BillRepository
) {

    class AddBillFailure(val errors: List<ErrorType>) : Failure.FeatureFailure() {
        enum class ErrorType {
            INVALID_VALUE, INVALID_DESCRIPTION
        }
    }

    suspend operator fun invoke(bill: Bill): Result<Bill> {

        val errors = arrayListOf<AddBillUseCase.AddBillFailure.ErrorType>()

        takeIf { bill.value == 0.0 }?.let { errors.add(AddBillFailure.ErrorType.INVALID_VALUE) }
        takeIf { bill.description.isNullOrEmpty() }?.let { errors.add(AddBillFailure.ErrorType.INVALID_DESCRIPTION) }

        return if (errors.isEmpty()) {
            billRepository.addBill(bill)
        } else {
            Result.Error(AddBillFailure(errors))
        }
    }
}