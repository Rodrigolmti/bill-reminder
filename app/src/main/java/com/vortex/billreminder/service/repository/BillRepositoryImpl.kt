package com.vortex.billreminder.service.repository

import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.repository.BillRepository
import com.vortex.billreminder.service.mappers.mapBillStorageToBill
import com.vortex.billreminder.service.mappers.mapBillToBillStorage
import com.vortex.billreminder.service.mappers.mapCategoryJsonToCategoryStorage
import com.vortex.billreminder.storage.database.RoomDatabase
import com.vortex.billreminder.storage.json.JsonParser
import com.vortex.billreminder.storage.model.CategoryDTO
import com.vortex.billreminder.storage.model.CategoryStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class BillRepositoryImpl(
    private val roomDatabase: RoomDatabase
) : BillRepository {

    override suspend fun getBillList(): Result<List<Bill>> = withContext(Dispatchers.IO) {
        suspendCoroutine<Result<List<Bill>>> { continuation ->
            try {

                val response = roomDatabase.database().billDao().findAll()
                continuation.resume(Result.Success(response.map { bill -> bill.mapBillToBillStorage() }))

            } catch (error: Exception) {
                continuation.resumeWithException(error)
            }
        }
    }

    override suspend fun addBill(bill: Bill): Result<Bill> = withContext(Dispatchers.IO) {
        suspendCoroutine<Result<Bill>> { continuation ->
            try {

                val billStorage = bill.mapBillStorageToBill()
                roomDatabase.database().billDao().insert(billStorage)
                continuation.resume(Result.Success(bill))

            } catch (error: Exception) {
                continuation.resumeWithException(error)
            }
        }
    }

    override suspend fun deleteBill(bill: Bill): Result<Bill> = withContext(Dispatchers.IO) {
        suspendCoroutine<Result<Bill>> { continuation ->
            try {

                val billStorage = bill.mapBillStorageToBill()
                roomDatabase.database().billDao().delete(billStorage)
                continuation.resume(Result.Success(bill))

            } catch (error: Exception) {
                continuation.resumeWithException(error)
            }
        }
    }

    override suspend fun updateBill(bill: Bill): Result<Bill> = withContext(Dispatchers.IO) {
        suspendCoroutine<Result<Bill>> { continuation ->
            try {

                val billStorage = bill.mapBillStorageToBill()
                roomDatabase.database().billDao().update(billStorage)
                continuation.resume(Result.Success(bill))

            } catch (error: Exception) {
                continuation.resumeWithException(error)
            }
        }
    }
}