package com.example.skeleton.navigation
enum class SkeletonScreens {
    SignInScreen;
    companion object {
        fun fromRoute(route: String?): SkeletonScreens =
            when (route?.substringBefore("/")) {
                SignInScreen.name -> SignInScreen
                null -> SignInScreen
                else -> throw IllegalArgumentException("Invalid route")
            }
    }
}