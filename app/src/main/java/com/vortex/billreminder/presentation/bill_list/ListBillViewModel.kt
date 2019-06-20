package com.vortex.billreminder.presentation.bill_list

import androidx.lifecycle.*
import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.use_case.GetListOfBillUseCase
import com.vortex.billreminder.util.BaseViewModel
import java.util.*

class ListBillViewModel(
    private val getListOfBillUseCase: GetListOfBillUseCase
) : BaseViewModel(), LifecycleObserver {

    private val _billListMutableLiveDate = MutableLiveData<List<Bill>>()

    val billListLiveDate: LiveData<List<Bill>> = _billListMutableLiveDate

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        launchData {
            when(val response = getListOfBillUseCase.invoke()) {
                is Result.Success -> { _billListMutableLiveDate.value = response.data }
                is Result.Error -> {}
            }
        }
    }
}
