package com.example.skeleton.viewmodel

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R) : ResultOf<R>()
    data class Failure(
        val message: String? = "",
        val throwable: Throwable? = null
    ) : ResultOf<Nothing>()
}

inline fun <reified T> ResultOf<Any>.checkEntity(): ResultOf<T> {
    return when (this) {
        is ResultOf.Failure -> this
        is ResultOf.Success -> {
            if (value is T) {
                ResultOf.Success(value)
            } else ResultOf.Failure("Different Response")
        }
    }
}