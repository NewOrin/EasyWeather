package com.neworin.easyweather

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.orhanobut.logger.Logger


/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
class TestService : Service() {

    var mIsStartThread = true

    override fun onBind(intent: Intent?): IBinder = null!!

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logger.d("服务启动")
        Thread(Runnable {
            while (mIsStartThread) {
                Logger.d("发送了...")
                sendBroadcast(Intent("com.neworin.easyweather.action.CLICK"))
            }
        }).start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mIsStartThread = false
    }
}