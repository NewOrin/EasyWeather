package com.neworin.easyweather.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonUtil {
    // 将Json数据解析成相应的映射对象
    fun <T> parseJsonWithGson(jsonData: String, type: Class<T>): T {
        val gson = Gson()
        val result = gson.fromJson(jsonData, type)
        return result
    }

    // 将Json数组解析成相应的映射对象列表
    fun <T> parseJsonArrayWithGson(jsonData: String,
                                   type: Class<T>): List<T> {
        val gson = Gson()
        val result = gson.fromJson<List<T>>(jsonData, object : TypeToken<List<T>>() {

        }.type)
        return result
    }
}