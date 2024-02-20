package com.example.skeleton.domain.usecase

import com.example.skeleton.viewmodel.ResultOf

abstract class BaseUseCase<in Params, Result> {
    suspend operator fun invoke(params: Params): ResultOf<Result> =
        execute(params)

    protected abstract suspend fun execute(params: Params): ResultOf<Result>

}