package com.example.skeleton.domain.usecase

import com.example.skeleton.domain.entity.UserEntity
import com.example.skeleton.model.request.signin.SignInParam
import com.example.skeleton.model.request.signin.SignInRequestFactory
import com.example.skeleton.repository.siginin.SignInRepository
import com.example.skeleton.viewmodel.ResultOf
import com.example.skeleton.viewmodel.checkEntity


class SignInUseCase(private val repo: SignInRepository) : BaseUseCase<SignInParam, UserEntity>() {
    override suspend fun execute(params: SignInParam): ResultOf<UserEntity> {
        val data = repo.fetchData(
            cash = true,
            requestFactory = SignInRequestFactory(signInParam = params),
            responseType = UserEntity::class.java
        )
        return data.checkEntity()
    }
}