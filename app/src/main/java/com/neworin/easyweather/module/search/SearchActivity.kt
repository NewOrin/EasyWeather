package com.neworin.easyweather.module.search

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import com.neworin.easyweather.R
import com.neworin.easyweather.adapter.ListViewAdapter
import com.neworin.easyweather.databinding.ActivitySearchBinding
import com.neworin.easyweather.entity.db.Citys
import com.neworin.easyweather.module.home.view.ISearchView
import com.neworin.easyweather.module.search.presenter.SearchPresenter
import com.neworin.easyweather.utils.Constant

class SearchActivity : AppCompatActivity(), ISearchView {

    var mBinding: ActivitySearchBinding? = null

    val mPresenter = SearchPresenter(this, this)

    var mAdapter: ListViewAdapter? = null

    var mDatas: List<Citys> = ArrayList()

    override fun refreshData(cityList: List<Citys>) {
        mDatas = cityList
        mAdapter?.updateDatas(cityList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
        initEvent()
    }

    fun initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        mAdapter = ListViewAdapter(mDatas, this)
        mBinding!!.searchListview.adapter = mAdapter
        mBinding!!.searchSearchview.onActionViewExpanded()
        mBinding!!.searchSearchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mPresenter.searchCity(newText)
                return true
            }
        })
    }

    fun initEvent() {
        mBinding!!.searchListview.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent()
                val bundle = Bundle()
            bundle.putSerializable(Constant.INTENT_KEY_STR_01, mDatas[position].city_num)
                intent.putExtras(bundle)
//                mPresenter.insertSharedCity(mDatas[position].basic!!)
                setResult(Constant.INTENT_KEY_CODE_01, intent)
                finish()
        }
    }

    fun queryCity(qStr: String) {

    }
}
