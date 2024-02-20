package com.example.skeleton.model.request

import com.example.skeleton.model.response.Account
import kotlinx.serialization.Serializable

@Serializable
open class BaseRequestParam(var account: Account? = null)
