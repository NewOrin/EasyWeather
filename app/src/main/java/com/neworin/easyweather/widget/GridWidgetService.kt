package com.neworin.easyweather.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import android.widget.Toast
import com.neworin.easyweather.R
import com.neworin.easyweather.entity.H5Weather
import com.neworin.easyweather.entity.HourlyForecast
import com.neworin.easyweather.entity.Weather
import com.neworin.easyweather.utils.Constant
import com.neworin.easyweather.widget.model.WidgetModelImpl
import com.neworin.easyweather.widget.presenter.GridViewPresenter
import com.neworin.easyweather.widget.view.IGridView
import com.orhanobut.logger.Logger
import java.util.ArrayList

/**
 * Author: zhangfubin
 * Description:
 */
class GridWidgetService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return WidgetServiceFactory(this, intent!!)
    }

    class WidgetServiceFactory(val ctx: Context, var intent: Intent) : RemoteViewsService.RemoteViewsFactory, IGridView {

        var mWeather = Weather()

        var mAppWidgetId: Int = 0

        val mPresenter = GridViewPresenter(ctx, WidgetModelImpl(), this)

        init {
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        }

        override fun refreshData(h5Weather: H5Weather, context: Context) {
            mDatas = h5Weather.HeWeather5[0].hourly_forecast as ArrayList<HourlyForecast>
        }

        override fun refreshFailed(errorMsg: String, context: Context) {
            Toast.makeText(context, "data update failed", Toast.LENGTH_SHORT).show()
        }

        override fun getLoadingView(): RemoteViews? {
            return null
        }

        override fun onDataSetChanged() {
            mPresenter.getWeather()
        }

        override fun hasStableIds(): Boolean = true

        override fun getViewTypeCount(): Int = 1

        override fun onDestroy() {
            mDatas.clear()
        }

        var mDatas = ArrayList<HourlyForecast>()

        override fun onCreate() {
        }

        override fun getViewAt(position: Int): RemoteViews {
            val rv = RemoteViews(ctx.packageName, R.layout.item_weather_widget_gridview_layout)
            rv.setTextViewText(R.id.item_weather_widget_gridview_title_tv, mDatas[position].tmp)
            val fillIntent = Intent()
            fillIntent.putExtra(Constant.CLICK_ACTION, position)
            rv.setOnClickFillInIntent(R.id.item_weather_widget_gridview_title_tv, fillIntent)
            return rv
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mDatas.size
        }
    }
}