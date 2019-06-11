package com.vortex.billreminder.presentation.bill_list

import com.vortex.billreminder.domain.use_case.GetListOfBillUseCase
import com.vortex.billreminder.util.BaseViewModel

class ListBillViewModel(
    private val getListOfBillUseCase: GetListOfBillUseCase
) : BaseViewModel() {


}
