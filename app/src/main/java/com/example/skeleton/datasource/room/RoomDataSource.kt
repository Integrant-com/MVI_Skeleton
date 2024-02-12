package com.example.skeleton.datasource.room

import com.example.skeleton.domain.entity.BaseModelEntity


interface RoomDataSource {
    suspend fun saveObject(model: BaseModelEntity?)
    suspend fun getCachedObject(): BaseModelEntity?
    suspend fun clearDatabase() : Int
}