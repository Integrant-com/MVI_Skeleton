package com.example.skeleton.model.response

import com.example.skeleton.domain.entity.UserEntity
import com.example.skeleton.model.BaseModel
import com.example.skeleton.model.room.AccountTable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Account(
    @SerialName("user") val user: User,
    @SerialName("token") val token: String
) : BaseModel()

@Serializable
data class User(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("email") var email: String? = null,
    @SerialName("mobile") var mobile: String? = null,
    @SerialName("image") var image: String? = null
)

fun Account.domainToRoom(): AccountTable {
    return AccountTable(user.id ?: 0, token)
}
