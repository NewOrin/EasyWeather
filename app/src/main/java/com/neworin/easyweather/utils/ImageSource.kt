package com.neworin.easyweather.utils

import com.neworin.easyweather.R

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
object ImageSource {
    fun getResouceId(code: Int): Int {
        var drawableId: Int = R.drawable.ic_999
        when (code) {
            100 -> drawableId = R.drawable.ic_100
            101 -> drawableId = R.drawable.ic_101
            102 -> drawableId = R.drawable.ic_102
            103 -> drawableId = R.drawable.ic_103
            104 -> drawableId = R.drawable.ic_104
            200 -> drawableId = R.drawable.ic_200
            201 -> drawableId = R.drawable.ic_201
            202 -> drawableId = R.drawable.ic_202
            203 -> drawableId = R.drawable.ic_203
            204 -> drawableId = R.drawable.ic_204
            205 -> drawableId = R.drawable.ic_205
            206 -> drawableId = R.drawable.ic_206
            207 -> drawableId = R.drawable.ic_207
            208 -> drawableId = R.drawable.ic_208
            209 -> drawableId = R.drawable.ic_209
            210 -> drawableId = R.drawable.ic_210
            211 -> drawableId = R.drawable.ic_211
            212 -> drawableId = R.drawable.ic_212
            213 -> drawableId = R.drawable.ic_213
            300 -> drawableId = R.drawable.ic_300
            301 -> drawableId = R.drawable.ic_301
            302 -> drawableId = R.drawable.ic_302
            303 -> drawableId = R.drawable.ic_303
            304 -> drawableId = R.drawable.ic_304
            305 -> drawableId = R.drawable.ic_305
            306 -> drawableId = R.drawable.ic_306
            307 -> drawableId = R.drawable.ic_307
            308 -> drawableId = R.drawable.ic_308
            309 -> drawableId = R.drawable.ic_309
            310 -> drawableId = R.drawable.ic_310
            311 -> drawableId = R.drawable.ic_311
            312 -> drawableId = R.drawable.ic_312
            313 -> drawableId = R.drawable.ic_313
            400 -> drawableId = R.drawable.ic_400
            401 -> drawableId = R.drawable.ic_401
            402 -> drawableId = R.drawable.ic_402
            403 -> drawableId = R.drawable.ic_403
            404 -> drawableId = R.drawable.ic_404
            405 -> drawableId = R.drawable.ic_405
            406 -> drawableId = R.drawable.ic_406
            407 -> drawableId = R.drawable.ic_407
            500 -> drawableId = R.drawable.ic_500
            501 -> drawableId = R.drawable.ic_501
            502 -> drawableId = R.drawable.ic_502
            503 -> drawableId = R.drawable.ic_503
            504 -> drawableId = R.drawable.ic_504
            507 -> drawableId = R.drawable.ic_507
            508 -> drawableId = R.drawable.ic_508
            900 -> drawableId = R.drawable.ic_900
            901 -> drawableId = R.drawable.ic_901
            999 -> drawableId = R.drawable.ic_999
        }
        return drawableId
    }
}