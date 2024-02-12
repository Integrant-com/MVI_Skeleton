package com.example.skeleton.features.sigin.uimodel

sealed class SignInIntent {

    data object SignIn : SignInIntent()
    data class UpdateUsername(val username: String) : SignInIntent()
    data class UpdatePassword(val password: String) : SignInIntent()
    data object HideError : SignInIntent()
}