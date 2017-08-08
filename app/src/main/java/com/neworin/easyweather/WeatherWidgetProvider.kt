package com.neworin.easyweather

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast

open class WeatherWidgetProvider : AppWidgetProvider() {

    val CLICK_ACTION: String = "com.neworin.easyweather.action.CLICK"

    /**
     * 每次窗口小部件被更新都调用一次该方法
     */
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        val remoteViews = RemoteViews(context?.packageName, R.layout.activity_main)
        val intent = Intent(CLICK_ACTION)
        val pendingIntent = PendingIntent.getBroadcast(context, R.id.widget_image, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteViews.setOnClickPendingIntent(R.id.widget_image, pendingIntent)
        for (widgetId in appWidgetIds!!) {
            appWidgetManager?.updateAppWidget(widgetId, remoteViews)
        }
    }

    /**
     * 接收窗口小部件点击时发送的广播
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (CLICK_ACTION == intent?.action) {
            Toast.makeText(context, "item", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * 当小部件从备份恢复时调用该方法
     */
    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        super.onRestored(context, oldWidgetIds, newWidgetIds)
    }
    /**
     * 当该窗口小部件第一次添加到桌面时调用该方法
     */
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    /**
     * 每删除一次窗口小部件就调用一次
     */
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    /**
     * 当小部件大小改变时
     */
    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }
}