package com.neworin.easyweather.http.convert;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.neworin.easyweather.entity.H5Weather;
import com.neworin.easyweather.entity.Weather;
import com.neworin.easyweather.util.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
public class WResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Gson mGson;

    public WResponseBodyConverter(Gson gson) {
        this.mGson = gson;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        JSONObject jsonObject = null;
        List<Weather> weatherList = new ArrayList<>();
        H5Weather h5Weather = JSON.parseObject(response, H5Weather.class);
        weatherList = h5Weather.HeWeather5;
//        try {
//            jsonObject = new JSONObject(response);
//            weatherList = JSON.parseArray(jsonObject.toString(), Weather.class);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return (T) weatherList;
    }
}
