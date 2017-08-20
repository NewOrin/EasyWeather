package com.neworin.easyweather.http

import com.neworin.easyweather.utils.Constant
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import okhttp3.Request

class CommonInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //获得请求信息，此处如有需要可以添加headers信息
        val request = chain.request()
        val newRequest = addParam(request)
        //添加Cookie信息
//        request.newBuilder().addHeader("Cookie", "aaaa")
        //打印请求信息
        Logger.d("method : " + newRequest.method() + "\n" + "request url : " + newRequest.url() + "\n" + "request body : " + newRequest.body())

        //记录请求耗时
        val startNs = System.nanoTime()
        val response: okhttp3.Response
        try {
            //发送请求，获得相应，
            response = chain.proceed(newRequest)
        } catch (e: Exception) {
            throw e
        }

        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        //打印请求耗时

        //获得返回的body，注意此处不要使用responseBody.string()获取返回数据，原因在于这个方法会消耗返回结果的数据(buffer)
        val responseBody = response.body()

        //为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
        val source = responseBody.source()
        source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
        //获得返回的数据
        val buffer = source.buffer()
        //使用前clone()下，避免直接消耗
        Logger.d("take time : " + tookMs + "ms \n" + "response : " + buffer.clone().readString(Charset.forName("UTF-8")))
        return response
    }

    /**
     * 添加公共参数
     * @param oldRequest
     * @return
     */
    private fun addParam(oldRequest: Request): Request {

        val builder = oldRequest.url()
                .newBuilder()
                .setEncodedQueryParameter("latitude", "0")
                .setEncodedQueryParameter("longitude", "0")
                .setEncodedQueryParameter("sign", Constant.XIAOMI_WEATHER_SIGN)
                .setEncodedQueryParameter("appKey", "weather20151024")
                .setEncodedQueryParameter("isGlobal", false.toString())
                .setEncodedQueryParameter("locale", "zh_cn")

        val newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(builder.build())
                .build()

        return newRequest
    }
}