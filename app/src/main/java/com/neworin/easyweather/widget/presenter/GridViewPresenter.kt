package com.neworin.easyweather.widget.presenter

import android.content.Context
import com.alibaba.fastjson.JSON
import com.neworin.easyweather.entity.Basic
import com.neworin.easyweather.entity.H5Weather
import com.neworin.easyweather.utils.Constant
import com.neworin.easyweather.utils.SharedPreferenceUtil
import com.neworin.easyweather.widget.model.WidgetModelImpl
import com.neworin.easyweather.widget.view.IGridView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
class GridViewPresenter(val mContext: Context, val mWidgetModel: WidgetModelImpl, val mView: IGridView) {

    fun getWeather() {
        mWidgetModel.getHourlyWeather(getCurrentCity(), object : Callback<H5Weather> {
            override fun onResponse(call: Call<H5Weather>, response: Response<H5Weather>) {
                mView.refreshData(response.body(),mContext)
            }

            override fun onFailure(call: Call<H5Weather>, t: Throwable) {
                mView.refreshFailed(t.message!!,mContext)
            }
        })
    }


    /**
     * 获取当前城市
     */
    fun getCurrentCity(): String {
        val basicStr = SharedPreferenceUtil.getString(mContext.applicationContext, Constant.SHARED_CITIES_TAG)
        val basicList = JSON.parseArray(basicStr, Basic::class.java)
        return basicList[0].city!!
    }
}