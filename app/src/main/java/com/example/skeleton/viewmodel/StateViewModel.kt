package com.example.skeleton.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeleton.domain.uistate.BaseViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


abstract class StateViewModel<STATE, INTENT> : ViewModel() {

    // Create Initial State of View
    private val initialState: BaseViewState<STATE> by lazy { createInitialState() }

    abstract fun handleIntent(event: INTENT)

    private val _state: MutableStateFlow<BaseViewState<STATE>> =
        MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    fun sendState(state: BaseViewState<STATE>) {
        viewModelScope.launch { _state.emit(state) }
    }

    abstract fun createInitialState(): BaseViewState<STATE>


    fun setUiModel(newState: BaseViewState<STATE>, data: STATE): BaseViewState<STATE> =
        BaseViewState(
            error = newState.error,
            isSuccess = newState.isSuccess,
            isLoading = newState.isLoading,
            data = data
        )
}