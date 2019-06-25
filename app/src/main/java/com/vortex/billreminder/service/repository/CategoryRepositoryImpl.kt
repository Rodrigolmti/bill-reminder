package com.vortex.billreminder.service.repository

import com.vortex.billreminder.domain.Result
import com.vortex.billreminder.domain.repository.CategoryRepository
import com.vortex.billreminder.service.mappers.mapCategoryJsonToCategoryStorage
import com.vortex.billreminder.storage.database.RoomDatabase
import com.vortex.billreminder.storage.json.JsonParser
import com.vortex.billreminder.storage.model.CategoryDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CategoryRepositoryImpl(
    private val roomDatabase: RoomDatabase,
    private val jsonParser: JsonParser
) : CategoryRepository {

    override suspend fun checkInitialData(): Result<Boolean> = withContext(Dispatchers.IO) {
        suspendCoroutine<Result<Boolean>> { continuation ->
            try {

                val response = roomDatabase.database().categoryDao().findAll()
                continuation.resume(Result.Success(response.isNotEmpty()))

            } catch (error: Exception) {
                continuation.resumeWithException(error)
            }
        }
    }

    override suspend fun populateInitialData(): Result<Boolean> = withContext(Dispatchers.IO) {
        suspendCoroutine<Result<Boolean>> { continuation ->
            try {

                val response = jsonParser.parseLocalJson("categories.json", CategoryDTO::class)
                if (response.categories.isNotEmpty()) {
                    roomDatabase.database().categoryDao()
                        .insertCategories(response.categories.map { item -> item.mapCategoryJsonToCategoryStorage() })
                    continuation.resume(Result.Success(true))
                    return@suspendCoroutine
                }

                continuation.resume(Result.Success(false))

            } catch (error: Exception) {
                continuation.resumeWithException(error)
            }
        }
    }
}