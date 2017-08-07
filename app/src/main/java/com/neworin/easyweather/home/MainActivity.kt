package com.neworin.easyweather.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.neworin.easyweather.R
import com.neworin.easyweather.entity.Weather
import com.neworin.easyweather.home.presenter.HomePresenter
import com.neworin.easyweather.home.view.IHomeView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IHomeView {

    var presenter = HomePresenter(this, this)

    override fun refreshData(list: List<Weather>) {
        Log.d("neworin", "获取天气 : " + list.size)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getCityWeather("赣州")
    }
}
