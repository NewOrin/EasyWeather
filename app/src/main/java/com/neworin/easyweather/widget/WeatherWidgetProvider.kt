package com.neworin.easyweather.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.neworin.easyweather.R
import com.neworin.easyweather.entity.Basic
import com.neworin.easyweather.entity.H5Weather
import com.neworin.easyweather.module.home.model.HomeModelImpl
import com.neworin.easyweather.utils.Constant
import com.neworin.easyweather.utils.SharedPreferenceUtil
import com.neworin.easyweather.widget.model.WidgetModelImpl
import com.neworin.easyweather.widget.presenter.WidgetPresenter
import com.neworin.easyweather.widget.view.IWidgetView
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherWidgetProvider : AppWidgetProvider(), IWidgetView {

    /**
     * 刷新控件
     */
    override fun refreshWidget(h5Weather: H5Weather, context: Context) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val rv = RemoteViews(context.packageName, R.layout.weather_widget_layout)
        val tmp = h5Weather.HeWeather5[0].now?.tmp
        rv.setTextViewText(R.id.weather_widget_show_tmp_tv, " $tmp °C")
        rv.setTextViewText(R.id.weather_widget_city_tv, h5Weather.HeWeather5[0].basic?.city)
        val cond = h5Weather.HeWeather5[0].now?.cond?.txt
        val max = h5Weather.HeWeather5[0].daily_forecast?.get(0)?.tmp?.max
        val min = h5Weather.HeWeather5[0].daily_forecast?.get(0)?.tmp?.min
        rv.setTextViewText(R.id.weather_widget_round_tv, "$cond $min °- $max °C")
        //设置gridview的adapter
        val gridIntent = Intent(context, GridWidgetService::class.java)
        val bundle = Bundle()
        bundle.putSerializable(Constant.INTENT_KEY_STR_01, h5Weather.HeWeather5[0])
        gridIntent.putExtras(bundle)
        gridIntent.putExtra("test","hello")
        rv.setRemoteAdapter(R.id.weather_widget_gridview, gridIntent)
        appWidgetManager.updateAppWidget(ComponentName(context, WeatherWidgetProvider::class.java), rv)
        Toast.makeText(context, context.getString(R.string.weather_widget_update_success_hint), Toast.LENGTH_SHORT).show()
    }

    override fun refreshFailed(errorMsg: String, context: Context) {
        Toast.makeText(context, context.getString(R.string.weather_widget_update_failed_hint), Toast.LENGTH_SHORT).show()
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        for (appId in appWidgetIds!!) {
            val rv = RemoteViews(context?.packageName, R.layout.weather_widget_layout)
            val intentClick = Intent()
            intentClick.action = Constant.CLICK_ACTION
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0)
            rv.setOnClickPendingIntent(R.id.weather_widget_refresh_image, pendingIntent)
            val gridIntent = Intent(context, GridWidgetService::class.java)
            gridIntent.putExtra("test","hello12323")
            rv.setRemoteAdapter(R.id.weather_widget_gridview, gridIntent)
            appWidgetManager?.updateAppWidget(appId, rv)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (Constant.CLICK_ACTION == intent?.action) {
            Logger.d("开始更新天气")
//            updateWeather(context)
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
        val mPresenter = WidgetPresenter(context!!, WidgetModelImpl(), this)
        mPresenter.getWeather()
    }
}