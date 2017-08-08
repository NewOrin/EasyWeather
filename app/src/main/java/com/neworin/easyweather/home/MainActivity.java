package com.neworin.easyweather.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.neworin.easyweather.R;
import com.neworin.easyweather.databinding.ActivityMainBinding;
import com.neworin.easyweather.entity.Weather;
import com.neworin.easyweather.home.presenter.HomePresenter;
import com.neworin.easyweather.home.view.IHomeView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IHomeView {

    private ActivityMainBinding mBinding;
    private HomePresenter mHomePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mHomePresenter = new HomePresenter(this, MainActivity.this);
        mHomePresenter.getCityWeather("赣州");
    }

    @Override
    public void refreshData(@NotNull List<Weather> list) {
        mBinding.setNow(list.get(0).getNow());
    }
}