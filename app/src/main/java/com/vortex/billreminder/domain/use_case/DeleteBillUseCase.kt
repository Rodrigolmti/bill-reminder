package com.vortex.billreminder.domain.use_case

import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.repository.BillRepository
import com.vortex.billreminder.domain.Result

class DeleteBillUseCase(
    private val billRepository: BillRepository
) {

    suspend operator fun invoke(bill: Bill): Result<Bill> {
        return billRepository.deleteBill(bill)
    }
}