package com.vortex.billreminder.util

sealed class Result<out T : Any> {
    class Success<T : Any>(val data: T) : Result<T>()
    class Error(val error: Throwable) : Result<Nothing>()
}

abstract class BaseUseCase<out T : Any> {

    abstract operator fun invoke(): Result<T>
}