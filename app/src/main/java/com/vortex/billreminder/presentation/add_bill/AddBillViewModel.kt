package com.vortex.billreminder.presentation.add_bill

import androidx.lifecycle.*
import com.vortex.billreminder.domain.Failure
import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.model.Category
import com.vortex.billreminder.domain.use_case.AddBillUseCase
import com.vortex.billreminder.domain.use_case.GetCategoriesUseCase
import com.vortex.billreminder.util.BaseViewModel
import com.vortex.billreminder.util.LiveDataSingleEvent

class AddBillViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val addBillUseCase: AddBillUseCase
) : BaseViewModel(), LifecycleObserver {

    private val _errorMutableLiveData = MutableLiveData<LiveDataSingleEvent<Failure>>()
    private val _categoriesListMutableLiveData = MutableLiveData<List<Category>>()
    private val _addBillMutableLiveData = MutableLiveData<Bill>()

    val errorLiveData: LiveData<LiveDataSingleEvent<Failure>>
        get() = _errorMutableLiveData

    val categoriesList
        get() = _categoriesListMutableLiveData

    val addBillLiveData
        get() = _addBillMutableLiveData

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        launchData {
            when (val response = getCategoriesUseCase.invoke()) {
                is Result.Success -> {
                    _categoriesListMutableLiveData.value = response.data
                }
                is Result.Error -> {
                    _errorMutableLiveData.value = LiveDataSingleEvent(response.failure)
                }
            }
        }
    }

    fun addBill(bill: Bill) {
        launchData {
            when (val response = addBillUseCase.invoke(bill)) {
                is Result.Success -> {
                    _addBillMutableLiveData.value = response.data
                }
                is Result.Error -> {
                    _errorMutableLiveData.value = LiveDataSingleEvent(response.failure)
                }
            }
        }
    }
}
