package com.vortex.billreminder.di

import com.vortex.billreminder.domain.repository.BillRepository
import com.vortex.billreminder.domain.use_case.AddBillUseCase
import com.vortex.billreminder.domain.use_case.DeleteBillUseCase
import com.vortex.billreminder.domain.use_case.GetListOfBillUseCase
import com.vortex.billreminder.domain.use_case.UpdateBillUseCase
import com.vortex.billreminder.presentation.add_bill.AddBillViewModel
import com.vortex.billreminder.presentation.bill_list.ListBillViewModel
import com.vortex.billreminder.service.repository.BillRepositoryImpl
import com.vortex.billreminder.storage.database.RoomDatabase
import com.vortex.billreminder.storage.database.RoomDatabaseImpl
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val dataModule: Module = module {
    single { BillRepositoryImpl(get()) as BillRepository }
    single { RoomDatabaseImpl(get()) as RoomDatabase }

    factory { GetListOfBillUseCase(get()) }
    factory { AddBillUseCase(get()) }
    factory { DeleteBillUseCase(get()) }
    factory { UpdateBillUseCase(get()) }
}

val viewModelModule: Module = module {
    viewModel { AddBillViewModel(get()) }
    viewModel { ListBillViewModel(get()) }
}