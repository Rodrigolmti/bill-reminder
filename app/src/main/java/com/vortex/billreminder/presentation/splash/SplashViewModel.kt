package com.vortex.billreminder.presentation.splash

import androidx.lifecycle.*
import com.vortex.billreminder.domain.Failure
import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.use_case.CheckInitialDataUseCase
import com.vortex.billreminder.domain.use_case.PopulateInitialDataUseCase
import com.vortex.billreminder.util.BaseViewModel
import com.vortex.billreminder.util.LiveDataSingleEvent
import kotlinx.coroutines.Job

class SplashViewModel(
    private val checkInitialDataUseCase: CheckInitialDataUseCase,
    private val populateInitialDataUseCase: PopulateInitialDataUseCase
) : BaseViewModel(), LifecycleObserver {

    private val _errorMutableLiveData = MutableLiveData<LiveDataSingleEvent<Failure>>()
    private val _onValueSavedMutableLiveData = MutableLiveData<Boolean>()

    val errorLiveData: LiveData<LiveDataSingleEvent<Failure>>
        get() = _errorMutableLiveData
    val onValueSavedLiveData: LiveData<Boolean>
        get() = _onValueSavedMutableLiveData

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        launchData {
            when (val response = checkInitialDataUseCase.invoke()) {
                is Result.Success -> {
                    takeIf { response.data }?.let {
                        _onValueSavedMutableLiveData.value = true
                    } ?: run {
                        populateData()
                    }
                }
                is Result.Error -> {
                    _errorMutableLiveData.value = LiveDataSingleEvent(response.failure)
                }
            }
        }
    }

    private fun populateData(): Job {
        return launchData {
            when (val response = populateInitialDataUseCase.invoke()) {
                is Result.Success -> {
                    _onValueSavedMutableLiveData.value = true
                }
                is Result.Error -> {
                    _errorMutableLiveData.value = LiveDataSingleEvent(response.failure)
                }
            }
        }
    }
}