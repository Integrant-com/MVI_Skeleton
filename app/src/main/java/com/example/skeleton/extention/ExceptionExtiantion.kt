package com.example.skeleton.extention


import com.example.skeleton.model.BaseModel
import com.example.skeleton.util.network.NetworkUtil
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlin.coroutines.cancellation.CancellationException

suspend fun Exception.getFetchDataException(): Pair<String?, Exception> {
    val serverMessage = if (this is ResponseException) response.body<BaseModel>().message
        ?: response.status.description else ""
    return when (this) {
        is RedirectResponseException -> Pair(serverMessage, this)
        is ClientRequestException -> Pair(serverMessage, this)
        is ServerResponseException -> Pair(serverMessage, this)
        is IOException -> Pair(NetworkUtil.NETWORK_ERROR_MSG, this)
        is CancellationException -> Pair("Cancellation Exception ", this)
        else -> Pair(NetworkUtil.CLIENT_ERROR_MSG, this)
    }
}