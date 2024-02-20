package com.example.skeleton.datasource.remote.signin

import com.example.skeleton.model.BaseModel
import com.example.skeleton.model.request.BaseRequestFactory
import com.example.skeleton.model.request.signin.SignInParam
import com.example.skeleton.model.request.signin.SignInRequestFactory
import com.example.skeleton.model.response.SignInResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody


open class SignInRemoteDataSourceImpl(private var api: HttpClient) : SignInRemoteDataSource {

    override suspend fun fetchData(requestFactory: BaseRequestFactory): BaseModel? {
        return when (requestFactory) {
            is SignInRequestFactory -> {
                val requestParam = requestFactory.baseRequestParam as SignInParam
                api.post(requestFactory.getUrl()){
                    headers{ requestFactory.getHeaderParam() }
                    setBody(requestParam)
                }.body<SignInResponse>()
            }
            else -> null
        }
    }
}
