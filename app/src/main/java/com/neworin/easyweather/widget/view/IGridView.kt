package com.neworin.easyweather.widget.view

import android.content.Context
import com.neworin.easyweather.entity.heweather.H5Weather

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
interface IGridView {

    fun refreshData(h5Weather: H5Weather, context: Context)

    fun refreshFailed(errorMsg: String,context: Context)
}