package com.neworin.easyweather.module.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.neworin.easyweather.R;
import com.neworin.easyweather.databinding.ActivityMainBinding;
import com.neworin.easyweather.entity.heweather.Basic;
import com.neworin.easyweather.entity.heweather.Weather;
import com.neworin.easyweather.entity.db.Citys;
import com.neworin.easyweather.module.home.presenter.HomePresenter;
import com.neworin.easyweather.module.home.view.IHomeView;
import com.neworin.easyweather.module.search.SearchActivity;
import com.neworin.easyweather.utils.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IHomeView, Toolbar.OnMenuItemClickListener {

    private ActivityMainBinding mBinding;
    private HomePresenter mHomePresenter;
    private String[] mPermissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_PERMISSION_CODE = 1002;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void checkPermssion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, mPermissions[0]) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, mPermissions[1]) == PackageManager.PERMISSION_GRANTED) {
                initView();
            } else {
                ActivityCompat.requestPermissions(this, mPermissions, REQUEST_PERMISSION_CODE);
            }
        }
    }
    private void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
        mBinding.toolbar.setOnMenuItemClickListener(this);
        mHomePresenter = new HomePresenter(this, MainActivity.this);
        if (mHomePresenter.getSharedPrefCity() != null) {
            mHomePresenter.getCityWeather(mHomePresenter.getSharedPrefCity().getId());
        }

        mBinding.mainSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    List<Citys> provincesList = new ArrayList<>();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            initView();
        }
    }

    @Override
    public void refreshData(@NotNull List<Weather> list) {
        mBinding.setNow(list.get(0).getNow());
        mBinding.setDailyForcast(list.get(0).getDaily_forecast().get(0));
        mBinding.toolbar.setTitle(list.get(0).getBasic().getCity());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.home_menu_search) {
            startActivityForResult(new Intent(MainActivity.this, SearchActivity.class), Constant.INSTANCE.getINTENT_KEY_CODE_01());
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.INSTANCE.getINTENT_KEY_CODE_01() && null != data) {
            Basic basic = mHomePresenter.getCityId(data);
            if (null != basic) {
                mHomePresenter.getCityWeather(basic.getId());
            }
        }
    }
}