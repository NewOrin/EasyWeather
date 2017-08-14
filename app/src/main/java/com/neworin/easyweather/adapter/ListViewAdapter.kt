package com.neworin.easyweather.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.neworin.easyweather.R
import com.neworin.easyweather.databinding.ItemSearchResultBinding
import com.neworin.easyweather.entity.Weather

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
class ListViewAdapter(var mDatas: List<Weather>, var mContext: Context) : BaseAdapter() {

    var mBinding: ItemSearchResultBinding? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        mBinding = DataBindingUtil.bind(LayoutInflater.from(mContext).inflate(R.layout.item_search_result, parent, false))
        if (mDatas[position].status == mContext.getString(R.string.item_search_result_ok_status)) {
            mBinding!!.city = "${mDatas[position].basic?.city} - ${mDatas[position].basic?.prov} - ${mDatas[position].basic?.cnty}"
        } else if (mDatas[position].status == mContext.getString(R.string.item_search_result_no_city_hint)) {
            mBinding!!.city = mContext.getString(R.string.item_search_no_result_hint)
        }
        return mBinding!!.root
    }

    override fun getItem(position: Int): Any {
        return mDatas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mDatas.size
    }

    fun updateDatas(weatherList: List<Weather>) {
        this.mDatas = weatherList
        notifyDataSetChanged()
    }
}