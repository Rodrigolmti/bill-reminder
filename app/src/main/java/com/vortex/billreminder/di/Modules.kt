package com.vortex.billreminder.di

import com.vortex.billreminder.domain.repository.BillRepository
import com.vortex.billreminder.domain.repository.CategoryRepository
import com.vortex.billreminder.domain.use_case.*
import com.vortex.billreminder.presentation.add_bill.AddBillViewModel
import com.vortex.billreminder.presentation.bill_list.ListBillViewModel
import com.vortex.billreminder.presentation.splash.SplashViewModel
import com.vortex.billreminder.service.repository.BillRepositoryImpl
import com.vortex.billreminder.service.repository.CategoryRepositoryImpl
import com.vortex.billreminder.storage.database.RoomDatabase
import com.vortex.billreminder.storage.database.RoomDatabaseImpl
import com.vortex.billreminder.storage.json.JsonParser
import com.vortex.billreminder.storage.json.JsonParserImpl
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val dataModule: Module = module {
    single { BillRepositoryImpl(get()) as BillRepository }
    single { CategoryRepositoryImpl(get(), get()) as CategoryRepository }
    single { RoomDatabaseImpl(get()) as RoomDatabase }
    single { JsonParserImpl(get()) as JsonParser }

    factory { GetListOfBillUseCase(get()) }
    factory { AddBillUseCase(get()) }
    factory { CheckInitialDataUseCase(get()) }
    factory { PopulateInitialDataUseCase(get()) }
    factory { DeleteBillUseCase(get()) }
    factory { UpdateBillUseCase(get()) }
}

val viewModelModule: Module = module {
    viewModel { SplashViewModel(get(), get()) }
    viewModel { AddBillViewModel(get()) }
    viewModel { ListBillViewModel(get()) }
}