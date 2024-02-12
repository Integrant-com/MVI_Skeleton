package com.example.skeleton.datasource.sharedpre


import com.example.skeleton.domain.entity.BaseModelEntity
import com.example.skeleton.extention.convertToModel
import com.example.skeleton.model.CacheEntry
import com.qpn.kamashka.eventy.util.parsing.ParsingHelper
import java.lang.reflect.Type

class BaseSharedPrefDataSourceImpl : BaseSharedPrefDataSource {
    override fun getCachedObject(type: Type): BaseModelEntity? {
        val entry: CacheEntry<Any>? = SecureSharedPref.getObject(type)
        val cachedObject: Any? = entry?.obj
        return if (cachedObject != null) {
            val mCachedObject: BaseModelEntity? =
                ParsingHelper.gson?.toJson(cachedObject)?.convertToModel(type)
            mCachedObject
        } else null
    }


    override fun <T> saveObject(instance: T, type: Type, lastModifiedDate: Long) =
        SecureSharedPref.edit().putObject(CacheEntry(instance, lastModifiedDate), type).apply()

    override fun clearAllCaches() {
        SecureSharedPref.edit().clear().apply()
    }

}