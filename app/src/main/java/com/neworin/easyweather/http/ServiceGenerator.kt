package com.neworin.easyweather.http

import com.neworin.easyweather.http.convert.WGsonConvertFactory
import com.neworin.easyweather.utils.Constant

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
class ServiceGenerator {
    init {
        //设定日志级别
        mHttpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    companion object {

        //声明日志类
        private val mHttpLoggingInterceptor = HttpLoggingInterceptor()

        private val httpClient = OkHttpClient.Builder().addInterceptor(mHttpLoggingInterceptor).addInterceptor(CommonInterceptor()).build()

        private val builder = Retrofit.Builder().baseUrl(Constant.APP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        fun createService(): WeatherService {
            val retrofit = builder.client(httpClient).build()
            return retrofit.create(WeatherService::class.java)
        }
    }
}
