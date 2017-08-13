package com.neworin.easyweather.module.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.neworin.easyweather.R;
import com.neworin.easyweather.databinding.ActivityMainBinding;
import com.neworin.easyweather.entity.Weather;
import com.neworin.easyweather.module.home.presenter.HomePresenter;
import com.neworin.easyweather.module.home.view.ISearchView;
import com.neworin.easyweather.module.search.SearchActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ISearchView, Toolbar.OnMenuItemClickListener {

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
        mBinding.toolbar.setTitle("赣州");
        setSupportActionBar(mBinding.toolbar);
        mBinding.toolbar.setOnMenuItemClickListener(this);
        mHomePresenter = new HomePresenter(this, MainActivity.this);
        mHomePresenter.getCityWeather("赣州");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public void refreshData(@NotNull List<Weather> list) {
        mBinding.setNow(list.get(0).getNow());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.home_menu_search) {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
        }
        return true;
    }
}