package com.vortex.billreminder.domain.use_case

import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.repository.BillRepository
import com.vortex.billreminder.util.Result

class AddBillUseCase(
    private val billRepository: BillRepository
)  {

    suspend operator fun invoke(bill: Bill): Result<Bill> {

        //TODO: Validate the params of the bill ...

        return billRepository.addBill(bill)

    }
}