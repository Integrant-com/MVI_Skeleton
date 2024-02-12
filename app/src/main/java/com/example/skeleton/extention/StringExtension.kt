package com.example.skeleton.extention

import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.util.Patterns
import com.qpn.kamashka.eventy.util.parsing.ParsingHelper
import java.lang.reflect.Type

fun <T> String.convertToModel(type: Type): T? {
    return ParsingHelper.gson?.fromJson<T>(this, type)
}

fun String?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.toFormattedString() = Html.fromHtml(this, FROM_HTML_MODE_LEGACY).toString()



