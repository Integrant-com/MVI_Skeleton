package com.example.skeleton.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


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

