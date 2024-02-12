package com.example.skeleton.datasource.room.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.skeleton.domain.entity.UserEntity

@Entity(tableName = "account_table")
data class AccountTable(
    @PrimaryKey
    var accountId: Int,

    @ColumnInfo(name = "token")
    var token: String,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "email")
    val email: String? = null,

    @ColumnInfo(name = "mobile")
    val mobile: String? = null,

    @ColumnInfo(name = "image")
    val image: String? = null,
)

fun AccountTable.domainToModel(): UserEntity {
    return UserEntity(accountId, name, email, mobile, image, token)
}

fun UserEntity.domainToRoom(): AccountTable {
    return AccountTable(id ?: 0, token, name, email, mobile,image)
}