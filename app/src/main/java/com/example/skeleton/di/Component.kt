package com.example.skeleton.di

val networkComponent = listOf(networkModule)

val appComponents = listOf(
    baseSharedPrefDataSource,
    accountRoomDataSource,
    signIn,

    )