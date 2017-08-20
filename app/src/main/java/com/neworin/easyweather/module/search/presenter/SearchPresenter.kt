package com.neworin.easyweather.module.search.presenter

import android.content.Context
import android.content.SharedPreferences
import com.alibaba.fastjson.JSON
import com.neworin.easyweather.db.utils.DBManager
import com.neworin.easyweather.entity.Basic

import com.neworin.easyweather.entity.H5Weather
import com.neworin.easyweather.entity.db.Citys
import com.neworin.easyweather.module.home.model.HomeModelImpl
import com.neworin.easyweather.module.home.model.IHomeModel
import com.neworin.easyweather.module.home.view.ISearchView
import com.neworin.easyweather.module.search.model.SearchModel
import com.neworin.easyweather.module.search.model.SearchModelImpl
import com.neworin.easyweather.utils.Constant
import com.neworin.easyweather.utils.SharedPreferenceUtil
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
class SearchPresenter(private val mSearchView: ISearchView, private val mContext: Context) {

    private val mSearchModel: SearchModel

    init {
        this.mSearchModel = SearchModelImpl()
    }

    fun searchCity(city: String) {
        val dbManager = DBManager(mContext)
        dbManager.doQueryCity(city, object : Observer<List<Citys>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(citys: List<Citys>) {
//                provincesList = citys
            }

            override fun onError(e: Throwable) {
                Logger.d("get data error")
            }

            override fun onComplete() {
//                Logger.d("size = " + provincesList.size)
            }
        })
    }

    fun insertSharedCity(basic: Basic) {
        val cities = SharedPreferenceUtil.getString(mContext, Constant.SHARED_CITIES_TAG)
        var basicList = JSON.parseArray(cities, Basic::class.java)
        if (null == basicList) {
            basicList = ArrayList()
        }
        basicList.add(0, basic)
        SharedPreferenceUtil.putString(mContext, Constant.SHARED_CITIES_TAG, JSON.toJSONString(basicList))
    }
}