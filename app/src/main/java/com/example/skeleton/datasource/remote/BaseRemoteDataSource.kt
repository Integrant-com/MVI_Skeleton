package com.example.skeleton.datasource.remote

import com.example.skeleton.model.BaseModel
import com.example.skeleton.model.request.BaseRequestFactory

interface BaseRemoteDataSource {
    suspend fun fetchData(requestFactory: BaseRequestFactory): BaseModel?
}