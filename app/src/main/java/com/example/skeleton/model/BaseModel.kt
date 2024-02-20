package com.example.skeleton.model

import kotlinx.serialization.Serializable

@Serializable
open class BaseModel  {
    val message: String? = null
    var error: Int = 0
    open class Payload
}