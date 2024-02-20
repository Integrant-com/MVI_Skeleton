package com.example.skeleton.features.sigin.viewModel


import androidx.lifecycle.viewModelScope
import com.example.skeleton.domain.uistate.BaseViewState
import com.example.skeleton.domain.uistate.Error
import com.example.skeleton.domain.usecase.SignInUseCase
import com.example.skeleton.features.sigin.uimodel.SignInError
import com.example.skeleton.features.sigin.uimodel.SignInIntent
import com.example.skeleton.features.sigin.uimodel.SignInUiState
import com.example.skeleton.model.request.signin.SignInParam
import com.example.skeleton.viewmodel.ResultOf
import com.example.skeleton.viewmodel.StateViewModel

import kotlinx.coroutines.launch

class SignInViewModel(private val useCase: SignInUseCase) :
    StateViewModel<SignInUiState, SignInIntent>() {
    override fun handleIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.SignIn -> {
                signIn()
            }

            is SignInIntent.UpdateUsername -> {
                sendState(state.value.copy(data = state.value.data?.copy(userName = intent.username)))
            }

            is SignInIntent.UpdatePassword -> {
                sendState(state.value.copy(data = state.value.data?.copy(password = intent.password)))
            }

            is SignInIntent.HideError -> {
                sendState(state.value.copy(error = null))
            }
        }
    }

    private fun signIn() {
        if (state.value.data!!.userName.isEmpty()) {
            sendState(
                state.value.copy(
                    isSuccess = false,
                    isLoading = false,
                    error = SignInError.UserNameError("Error")
                )
            )
            return
        }
        if (state.value.data!!.password.isEmpty()) {
            sendState(
                state.value.copy(
                    isSuccess = false,
                    isLoading = false,
                    error = SignInError.PasswordError("Error")
                )
            )
            return
        }

        viewModelScope.launch {
            val data =
                useCase.invoke(
                    SignInParam(
                        state.value.data!!.userName,
                        state.value.data!!.password
                    )
                )
            when (data) {
                is ResultOf.Failure ->
                    sendState(
                        state.value.copy(
                            isSuccess = false,
                            isLoading = false,
                            error = Error.ServerError(data.message ?: "")
                        )
                    )


                is ResultOf.Success ->
                    sendState(
                        state.value.copy(isSuccess = true, isLoading = false, error = null)
                    )


            }
        }
    }

    override fun createInitialState(): BaseViewState<SignInUiState> {
        return BaseViewState(data = SignInUiState())
    }


}

