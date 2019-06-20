package com.vortex.billreminder.domain

sealed class Failure {
    object NotFound : Failure()
    object Unknown : Failure()
    data class ServiceError(val message: String, val errorCode: String? = null) : Failure()
    abstract class FeatureFailure : Failure()
}