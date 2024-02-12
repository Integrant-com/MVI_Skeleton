package com.example.skeleton.domain.entity

import com.example.skeleton.model.response.Account


data class UserEntity(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val mobile: String? = null,
    val image: String? = null,
    val token: String
) : BaseModelEntity()

fun Account.mapToUserEntity() =
    UserEntity(
        id = user.id,
        token = token,
        name = user.name,
        email = user.email,
        mobile = user.mobile,
        image = user.image
    )



