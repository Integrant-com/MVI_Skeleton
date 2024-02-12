package com.example.skeleton.model.response


import com.example.skeleton.model.BaseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    @SerialName("payload") val payload: SignInPayload?
) : BaseModel()

@Serializable
data class SignInPayload(
    @SerialName("data") val data: Account
) : BaseModel.Payload()
