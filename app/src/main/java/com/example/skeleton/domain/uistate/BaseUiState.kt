package com.example.skeleton.domain.uistate

data class BaseViewState<out T>(
    val isLoading: Boolean = false,
    val error: Error? = null,
    val data: T?,
    val isSuccess: Boolean = false
)

sealed class Error {
    data class ServerError(val message: String = "Server Error") : Error()
    open class UiError(open val message: String) : Error()
}
