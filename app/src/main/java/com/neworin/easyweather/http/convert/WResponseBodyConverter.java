package com.neworin.easyweather.http.convert;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.neworin.easyweather.entity.H5Weather;
import com.neworin.easyweather.entity.Weather;
import com.neworin.easyweather.util.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
        H5Weather h5Weather = null;
        h5Weather = GsonUtil.INSTANCE.parseJsonWithGson(response,H5Weather.class);
//        try {
//            jsonObject = new JSONObject(response);
//            String parseStr = jsonObject.get("HeWeather5").toString();
//            h5Weather = GsonUtil.parseJsonWithGson(parseStr,H5Weather.class);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return (T) h5Weather;
    }
}
