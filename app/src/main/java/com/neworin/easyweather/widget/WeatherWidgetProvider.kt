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
import com.neworin.easyweather.R
import com.neworin.easyweather.entity.heweather.H5Weather
import com.neworin.easyweather.utils.Constant
import com.neworin.easyweather.utils.ImageSource
import com.neworin.easyweather.widget.model.WidgetModelImpl
import com.neworin.easyweather.widget.presenter.WidgetPresenter
import com.neworin.easyweather.widget.view.IWidgetView

class WeatherWidgetProvider : AppWidgetProvider(), IWidgetView {

    /**
     * 刷新控件
     */
    override fun refreshWidget(h5Weather: H5Weather, context: Context, appId: Int) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val rv = RemoteViews(context.packageName, R.layout.weather_widget_layout)
        val tmp = h5Weather.HeWeather5[0].now?.tmp
        val code = h5Weather.HeWeather5[0].now?.cond?.code
        rv.setImageViewResource(R.id.weather_widget_show_cond_image, ImageSource.getResouceId(code!!.toInt()))
        rv.setTextViewText(R.id.weather_widget_show_tmp_tv, " $tmp °C")
        rv.setTextViewText(R.id.weather_widget_city_tv, h5Weather.HeWeather5[0].basic?.city)
//        val cond = h5Weather.HeWeather5[0].now?.cond?.txt
//        val max = h5Weather.HeWeather5[0].daily_forecast?.get(0)?.tmp?.max
//        val min = h5Weather.HeWeather5[0].daily_forecast?.get(0)?.tmp?.min
        appWidgetManager.notifyAppWidgetViewDataChanged(appId, R.id.weather_widget_gridview)
        appWidgetManager.updateAppWidget(ComponentName(context, WeatherWidgetProvider::class.java), rv)
        Toast.makeText(context, context.getString(R.string.weather_widget_update_success_hint), Toast.LENGTH_SHORT).show()
    }

    override fun refreshFailed(errorMsg: String, context: Context) {
        Toast.makeText(context, context.getString(R.string.weather_widget_update_failed_hint), Toast.LENGTH_SHORT).show()
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        for (appId in appWidgetIds!!) {
            val rv = RemoteViews(context?.packageName, R.layout.weather_widget_layout)
            val intentClick = Intent()
            intentClick.action = Constant.CLICK_ACTION
            intentClick.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appId)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0)
            rv.setOnClickPendingIntent(R.id.weather_widget_show_cond_image, pendingIntent)

            val gridIntent = Intent(context, GridWidgetService::class.java)
            rv.setRemoteAdapter(R.id.weather_widget_gridview, gridIntent)
            updateWeather(context, appId)
            appWidgetManager?.updateAppWidget(appId, rv)
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (Constant.CLICK_ACTION == intent?.action) {
            val appId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
            updateWeather(context, appId)
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
    fun updateWeather(context: Context?, appWidgetId: Int) {
        val mPresenter = WidgetPresenter(context!!, WidgetModelImpl(), this)
        mPresenter.getWeather(appWidgetId)
    }
}