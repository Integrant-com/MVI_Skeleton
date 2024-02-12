package com.example.skeleton.datasource.sharedpre

import com.example.skeleton.domain.entity.BaseModelEntity
import java.lang.reflect.Type
import java.util.*

interface BaseSharedPrefDataSource {
    fun getCachedObject(type: Type): BaseModelEntity?
    fun <T>saveObject(instance: T, type: Type, lastModifiedDate: Long = Date().time)
    fun clearAllCaches()
}