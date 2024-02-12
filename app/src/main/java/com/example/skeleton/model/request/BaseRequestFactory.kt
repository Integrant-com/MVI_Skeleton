package com.example.skeleton.model.request

import com.example.skeleton.model.ktor.Constant
import java.util.Locale


abstract class BaseRequestFactory {
    abstract var baseRequestParam: BaseRequestParam
    private var defaultProperties = HashMap<String, String>()
    var token: String? = null

    init {
        //val current = Locale.getDefault().language
        val default = "en"
        defaultProperties["x-localization"] = default
        defaultProperties["Accept"] = "application/json"
        defaultProperties["Connection"] = "close"
    }

    open fun getUrl() = Constant.BASE_URL + getEndPoint()
    open fun getCustomUrl() = "${getEndPoint()}"

    abstract fun getEndPoint(): String?

    fun getHeaderParam(): HashMap<String, String> {
        val headers = HashMap<String, String>()
        if (baseRequestParam.account != null)
            defaultProperties["Authorization"] = "Bearer ${baseRequestParam.account?.token}"
        else if (token != null)
            defaultProperties["Authorization"] = "Bearer $token"


        headers.putAll(defaultProperties)
        headers.putAll(getCustomHeaders())
        return headers
    }

    open fun getCustomHeaders(): HashMap<String, String> = HashMap()


}