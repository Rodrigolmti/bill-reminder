package com.vortex.billreminder.service.repository

import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.domain.repository.BillRepository
import com.vortex.billreminder.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class BillRepositoryImpl : BillRepository {

    override suspend fun getBillList(): Result<List<Bill>> = withContext(Dispatchers.IO) {
        suspendCoroutine<Result<List<Bill>>> { continuation ->
            try {



            } catch (error: Exception) {
                continuation.resumeWithException(error)
            }
        }
    }

    override suspend fun addBill(bill: Bill): Result<Bill> = withContext(Dispatchers.IO) {
        suspendCoroutine<Result<Bill>> { continuation ->
            try {



            } catch (error: Exception) {
                continuation.resumeWithException(error)
            }
        }
    }

    override suspend fun deleteBill(bill: Bill): Result<Bill> = withContext(Dispatchers.IO) {
        suspendCoroutine<Result<Bill>> { continuation ->
            try {



            } catch (error: Exception) {
                continuation.resumeWithException(error)
            }
        }
    }

    override suspend fun updateBill(bill: Bill): Result<Bill> = withContext(Dispatchers.IO) {
        suspendCoroutine<Result<Bill>> { continuation ->
            try {



            } catch (error: Exception) {
                continuation.resumeWithException(error)
            }
        }
    }
}