package com.neworin.easyweather.module.search

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import com.neworin.easyweather.R
import com.neworin.easyweather.databinding.ActivitySearchBinding
import com.orhanobut.logger.Logger

class SearchActivity : AppCompatActivity() {

    var mBinding: ActivitySearchBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    fun initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        mBinding!!.searchSearchview.onActionViewExpanded()
        mBinding!!.searchSearchview.isSubmitButtonEnabled = true
        mBinding!!.searchSearchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Logger.d("neworin", "newText = " + newText)
                return false
            }
        })
    }
}
