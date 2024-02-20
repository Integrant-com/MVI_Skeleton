package com.example.skeleton.model.request.signin

import com.example.skeleton.model.request.BaseRequestParam
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SignInParam(
    @SerialName("username")
    val name: String? = null,
    @SerialName("password")
    val password: String? = null
) : BaseRequestParam()
