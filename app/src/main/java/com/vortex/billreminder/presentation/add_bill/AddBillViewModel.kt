package com.vortex.billreminder.presentation.add_bill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vortex.billreminder.domain.Failure
import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.use_case.AddBillUseCase
import com.vortex.billreminder.util.BaseViewModel
import com.vortex.billreminder.util.LiveDataSingleEvent

class AddBillViewModel(
    private val addBillUseCase: AddBillUseCase
) : BaseViewModel() {

    private val _errorMutableLiveData = MutableLiveData<LiveDataSingleEvent<Failure>>()
    private val _addBillMutableLiveData = MutableLiveData<Bill>()

    val errorLiveData: LiveData<LiveDataSingleEvent<Failure>>
        get() = _errorMutableLiveData

    val addBillLiveData
        get () = _addBillMutableLiveData

    fun addBill(bill: Bill) {
        launchData {
            when (val response = addBillUseCase.invoke(bill)) {
                is Result.Success -> {
                    addBillLiveData.value = response.data
                }
                is Result.Error -> {
                    _errorMutableLiveData.value = LiveDataSingleEvent(response.failure)
                }
            }
        }
    }
}
