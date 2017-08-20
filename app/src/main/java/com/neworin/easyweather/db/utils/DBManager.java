package com.neworin.easyweather.db.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.neworin.easyweather.R;
import com.neworin.easyweather.entity.db.Citys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by NewOrin Zhang on 2017/8/20.
 * Project : com.neworin.easyweather.db.utils
 * Description:
 */

public class DBManager {
    private String DB_NAME = "weather.db";
    private String PACKAGE_NAME = "com.neworin.easyweather";
    private String DB_DIR = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME + "/databases/";
    private String DB_PATH = DB_DIR + DB_NAME;
    private Context mContext;
    private SQLiteDatabase mDB;

    public DBManager(Context mContext) {
        this.mContext = mContext;
    }

    private SQLiteDatabase getDB() {
        if (!new File(DB_PATH).exists()) {
            try {
                boolean flag = new File(DB_DIR).mkdirs();
                boolean newFile = new File(DB_PATH).createNewFile();
                try {
                    FileOutputStream out = new FileOutputStream(DB_PATH);
                    InputStream in = mContext.getResources().openRawResource(R.raw.weather);
                    byte[] buffer = new byte[1024];
                    int readBytes = 0;
                    while ((readBytes = in.read(buffer)) != -1)
                        out.write(buffer, 0, readBytes);
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mDB = SQLiteDatabase.openOrCreateDatabase(DB_PATH, null);
    }

    /**
     * 从数据库查询城市
     *
     * @param queryStr
     */
    private List<Citys> queryCities(String queryStr) {
        mDB = getDB();
        List<Citys> citysList = new ArrayList<>();
        if (null != mDB) {
            String sql = "select * from citys where name like ?";
            Cursor c = mDB.rawQuery(sql, new String[]{"%" + queryStr + "%"});
            while (c.moveToNext()) {
                int _id = c.getInt(c.getColumnIndexOrThrow("_id"));
                int province_id = c.getInt(c.getColumnIndexOrThrow("province_id"));
                String name = c.getString(c.getColumnIndexOrThrow("name"));
                String city_num = c.getString(c.getColumnIndexOrThrow("city_num"));
                String province_name = c.getString(c.getColumnIndexOrThrow("province_name"));
                citysList.add(new Citys(_id, province_id, name, city_num, province_name));
            }
            c.close();
        }
        return citysList;
    }

    public void doQueryCity(final String qStr, Observer<List<Citys>> receiver) {
        Observable<List<Citys>> queryObservalbe = Observable.create(new ObservableOnSubscribe<List<Citys>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Citys>> e) throws Exception {
                e.onNext(queryCities(qStr));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        queryObservalbe.subscribe(receiver);
    }
}
