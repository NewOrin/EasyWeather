package com.neworin.easyweather.widget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.neworin.easyweather.R
import com.neworin.easyweather.utils.Constant

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
class WeatherWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return WidgetServiceFactory(this, intent!!)
    }

    class WidgetServiceFactory(val ctx: Context, var intent: Intent) : RemoteViewsService.RemoteViewsFactory {
        override fun getLoadingView(): RemoteViews = null!!

        override fun onDataSetChanged() {
        }

        override fun hasStableIds(): Boolean = true

        override fun getViewTypeCount(): Int = 1

        override fun onDestroy() {
            mDatas.clear()
        }

        var mDatas = ArrayList<String>()

        override fun onCreate() {
            var x = 0
            while (x < 10) {
                mDatas.add("hell0 $x")
                x++
            }
        }

        override fun getViewAt(position: Int): RemoteViews {
            val rv = RemoteViews(ctx.packageName, R.layout.item_weather_widget_gridview_layout)
            rv.setTextViewText(R.id.item_weather_widget_gridview_title_tv, mDatas[position])
            val extras = Bundle()
            extras.putInt(Constant.CLICK_ACTION, position)
            val fillIntent = Intent()
            fillIntent.putExtras(extras)
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