package com.carl.mvpdemo.pub.network;

import retrofit2.Retrofit;

/**
 * @author Carl
 * version 1.0
 * @since 2018/6/10
 */
public class HttpClient {
    public RestAPI service;


    private static final HttpClient ourInstance = new HttpClient();

    public static HttpClient getInstance() {
        return ourInstance;
    }

    private HttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

        service = retrofit.create(RestAPI.class);

    }
}
