package com.neworin.easyweather.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
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
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        for (appId in appWidgetIds!!) {
            val rv = RemoteViews(context?.packageName, R.layout.weather_widget_layout)
            val intentClick = Intent()
            intentClick.action = Constant.CLICK_ACTION
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0)
            rv.setOnClickPendingIntent(R.id.weather_widget_refresh_image, pendingIntent)
            appWidgetManager?.updateAppWidget(appId, rv)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (Constant.CLICK_ACTION == intent?.action) {
            Logger.d("开始更新天气")
            updateWeather(context)
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

    /**
     * 更新天气网络请求
     */
    fun updateWeather(context: Context?) {
        val basicStr = SharedPreferenceUtil.getString(context?.applicationContext, Constant.SHARED_CITIES_TAG)
        val basicList = JSON.parseArray(basicStr, Basic::class.java)
        if (null != basicList) {
            val homeModel = HomeModelImpl()
            homeModel.getWeatherByCityName(basicList[0].city!!, object : Callback<H5Weather> {
                override fun onResponse(call: Call<H5Weather>?, response: Response<H5Weather>?) {
                    refreshWidget(context!!, response?.body()!!, true)
                }

                override fun onFailure(call: Call<H5Weather>?, t: Throwable?) {
                    refreshWidget(context!!, null!!, false)
                }
            })
        }
    }

    fun refreshWidget(ctx: Context, h5Weather: H5Weather, isSuccess: Boolean) {
        if (isSuccess) {
            val appWidgetManager = AppWidgetManager.getInstance(ctx)
            val rv = RemoteViews(ctx.packageName, R.layout.weather_widget_layout)
            val tmp = h5Weather.HeWeather5[0].now?.tmp
            rv.setTextViewText(R.id.weather_widget_show_tmp_tv, " $tmp °C")
            rv.setTextViewText(R.id.weather_widget_city_tv, h5Weather.HeWeather5[0].basic?.city)
            val cond = h5Weather.HeWeather5[0].now?.cond?.txt
            val max = h5Weather.HeWeather5[0].daily_forecast?.get(0)?.tmp?.max
            val min = h5Weather.HeWeather5[0].daily_forecast?.get(0)?.tmp?.min
            rv.setTextViewText(R.id.weather_widget_round_tv, "$cond $min °- $max °C")
            appWidgetManager.updateAppWidget(ComponentName(ctx, WeatherWidgetProvider::class.java), rv)
        }
    }
}