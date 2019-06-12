package com.vortex.billreminder.domain.use_case

import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.repository.BillRepository
import com.vortex.billreminder.util.Result

class GetListOfBillUseCase(
    private val billRepository: BillRepository
) {

    suspend operator fun invoke(): Result<List<Bill>> {
        return billRepository.getBillList()
    }
}