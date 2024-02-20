package com.example.skeleton.features.sigin.uimodel

import androidx.compose.ui.text.AnnotatedString
import com.example.skeleton.domain.uistate.Error


data class SignInUiState(
    val userName: String = "",
    val password: String = "",
    val passwordError: String? = null,
    val userNameError: String? = null,
    val navigateToHome: Boolean = false
)

sealed class SignInError(message: String) : Error.UiError(message) {
    data class PasswordError(override val message: String) :
        SignInError(message)

    data class UserNameError(override val message:String) :
        SignInError(message)
}