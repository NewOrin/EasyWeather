package com.neworin.easyweather.http.convert;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
public class WGsonConvertFactory extends Converter.Factory {

    private Gson mGson;

    public static WGsonConvertFactory create() {
        return create(new Gson());
    }

    private static WGsonConvertFactory create(Gson gson) {
        return new WGsonConvertFactory(gson);
    }

    private WGsonConvertFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.mGson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new WResponseBodyConverter<>(mGson);
    }
}
