package com.vortex.billreminder.di

import com.vortex.billreminder.domain.repository.BillRepository
import com.vortex.billreminder.domain.use_case.GetListOfBillUseCase
import com.vortex.billreminder.presentation.add_bill.AddBillViewModel
import com.vortex.billreminder.presentation.bill_list.ListBillViewModel
import com.vortex.billreminder.service.repository.BillRepositoryImpl
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val dataModule: Module = module {
    single { BillRepositoryImpl() as BillRepository }
    factory { GetListOfBillUseCase(get()) }
}

val viewModelModule: Module = module {
    viewModel { AddBillViewModel() }
    viewModel { ListBillViewModel(get()) }
}