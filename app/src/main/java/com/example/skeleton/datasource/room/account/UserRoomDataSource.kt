package com.example.skeleton.datasource.room.account

import com.example.skeleton.SkeletonApplication
import com.example.skeleton.datasource.room.SkeletonDatabase
import com.example.skeleton.datasource.room.RoomDataSource
import com.example.skeleton.domain.entity.BaseModelEntity
import com.example.skeleton.domain.entity.UserEntity


class AccountRoomDataSource : RoomDataSource {
    override suspend fun saveObject(model: BaseModelEntity?) {
        if (model is UserEntity) {
            SkeletonDatabase.getInstance(SkeletonApplication.getContext())?.accountDao?.clear()
            SkeletonDatabase.getInstance(SkeletonApplication.getContext())?.accountDao?.insert(model.domainToRoom())
        }
    }

    override suspend fun getCachedObject(): BaseModelEntity? {
        return SkeletonDatabase.getInstance(SkeletonApplication.getContext())?.accountDao?.getAccount()
            ?.domainToModel()
    }

    override suspend fun clearDatabase(): Int {
        return SkeletonDatabase.getInstance(SkeletonApplication.getContext())?.accountDao?.clear()
            ?: -1
    }
}