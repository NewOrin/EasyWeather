package com.neworin.easyweather.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RemoteViews
import com.alibaba.fastjson.JSON
import com.neworin.easyweather.R
import com.neworin.easyweather.entity.Basic
import com.neworin.easyweather.module.home.model.HomeModelImpl
import com.neworin.easyweather.utils.Constant
import com.neworin.easyweather.utils.SharedPreferenceUtil
import com.orhanobut.logger.Logger
import java.util.ArrayList
import com.neworin.easyweather.entity.H5Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherWidgetProvider : AppWidgetProvider() {
    var mContext: Context? = null
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        mContext = context

        for (appId in appWidgetIds!!) {
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (Constant.CLICK_ACTION == intent?.action) {
            Logger.d("接受到了")
            updateWeather()
        }
    }

    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        super.onRestored(context, oldWidgetIds, newWidgetIds)
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }

    fun updateWeather() {
        val basicStr = SharedPreferenceUtil.getString(mContext, Constant.SHARED_CITIES_TAG)
        val basicList = JSON.parseArray(basicStr, Basic::class.java)
        if (null != basicList) {
            val homeModel = HomeModelImpl()
            homeModel.getWeatherByCityName(basicList[0].city!!, object : Callback<H5Weather> {
                override fun onResponse(call: Call<H5Weather>?, response: Response<H5Weather>?) {

                }

                override fun onFailure(call: Call<H5Weather>?, t: Throwable?) {

                }
            })
        }
    }
}