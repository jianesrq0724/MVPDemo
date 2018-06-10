package com.carl.mvpdemo.pub.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Carl
 * version 1.0
 * @since 2018/6/10
 */
public class HttpClient {
    public RestAPI service;

    public static HttpClient getInstance(String baseUrl) {
        return new HttpClient(baseUrl);
    }

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)//超过时间
            .build();

    private HttpClient(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        service = retrofit.create(RestAPI.class);
    }
}
