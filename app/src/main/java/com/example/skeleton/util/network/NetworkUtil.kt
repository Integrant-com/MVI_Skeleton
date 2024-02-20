package com.example.skeleton.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.skeleton.R
import com.example.skeleton.SkeletonApplication


object NetworkUtil {
    const val NO_INTERNET_CONNECTION_CODE = "NIC"
    val CLIENT_ERROR_MSG =
        SkeletonApplication.getContext()?.getString(R.string.error_client) ?: SkeletonApplication.getContext()?.getString(R.string.try_again)
    val NETWORK_ERROR_MSG =
        SkeletonApplication.getContext()?.getString(R.string.no_internet_connection)
    val SERVER_ERROR_MSG =
        SkeletonApplication.getContext()?.getString(R.string.error_server)

    fun isNetworkAvailable(): Boolean {
        val result: Boolean
        val connectivityManager =
            SkeletonApplication.getContext()
                ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

}
