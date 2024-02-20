package com.example.skeleton.repository.siginin

import com.example.skeleton.datasource.remote.signin.SignInRemoteDataSource
import com.example.skeleton.datasource.room.account.AccountRoomDataSource
import com.example.skeleton.datasource.sharedpre.BaseSharedPrefDataSource
import com.example.skeleton.domain.entity.UserEntity
import com.example.skeleton.model.BaseModel
import com.example.skeleton.model.response.SignInResponse


class SignInRepositoryImpl(
    override val roomDataSource: AccountRoomDataSource,
    override val remoteRepo: SignInRemoteDataSource,
    override val sharedPrefDataSource: BaseSharedPrefDataSource
) : SignInRepository {
    override fun BaseModel.mapToEntity(): UserEntity {
        val token = (this as? SignInResponse)?.payload?.data?.token
        return UserEntity(token = token?:"")
    }
}

