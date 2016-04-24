package com.yani.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class APIFactory {

    private static final String API_URL = "http://cache-default03g.cdn.yandex.net/";

    private static final int TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 60;
    private static final int CONNECT_TIMEOUT = 20;

    private static final OkHttpClient CLIENT = new OkHttpClient();

    static {

        CLIENT.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);

    }

    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(CLIENT)
                .build();
    }

    @NonNull
    public static APIService getService() {
        return getRetrofit().create(APIService.class);
    }
}
