package com.example.skeleton.repository

import com.example.skeleton.datasource.remote.BaseRemoteDataSource
import com.example.skeleton.datasource.sharedpre.BaseSharedPrefDataSource
import com.example.skeleton.domain.entity.BaseModelEntity
import com.example.skeleton.domain.entity.UserEntity
import com.example.skeleton.extention.getFetchDataException
import com.example.skeleton.model.BaseModel
import com.example.skeleton.viewmodel.ResultOf
import com.example.skeleton.datasource.room.RoomDataSource
import com.example.skeleton.model.request.BaseRequestFactory
import java.lang.reflect.Type
import java.util.Date

interface BaseRepository  {

    val sharedPrefDataSource: BaseSharedPrefDataSource
    val remoteRepo: BaseRemoteDataSource
    val roomDataSource: RoomDataSource

    fun BaseModel.mapToEntity(): BaseModelEntity?
    suspend fun fetchData(
        shouldConnected: Boolean = true,
        cash: Boolean=false,
        requestFactory: BaseRequestFactory,
        responseType: Type
    ): ResultOf<BaseModelEntity> {

        return try {

            val cashed = roomDataSource.getCachedObject()
            if (!shouldConnected) {
                return if (cashed != null)
                    ResultOf.Success(value = cashed)
                else ResultOf.Failure("The variable is not cashed!", null)
            }

            requestFactory.token = (cashed as? UserEntity)?.token

            val response = remoteRepo.fetchData(requestFactory)

            if (response !is BaseModel)
                return ResultOf.Failure(message = "Response is not base model", null)


            val date = response.mapToEntity()
            if (cash) roomDataSource.saveObject(date)
            if (date == null) ResultOf.Failure(message = "Response is not base model", null)
            else
                ResultOf.Success(value = date)


        } catch (e: Exception) { // General Exception
            val exception = e.getFetchDataException()
            ResultOf.Failure(exception.first, exception.second)
        }
    }
    fun getCashedObjectSharedPref(type: Type): Any? = sharedPrefDataSource.getCachedObject(type)
    fun saveObjectSharedPref(
        instance: BaseModel?,
        type: Type,
        lastModifiedDate: Long = Date().time
    ) = sharedPrefDataSource.saveObject(instance, type, lastModifiedDate)

}
