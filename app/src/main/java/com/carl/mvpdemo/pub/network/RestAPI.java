package com.carl.mvpdemo.pub.network;

import com.carl.mvpdemo.module.home.bean.SmsBean;
import com.google.gson.JsonObject;

import java.util.TreeMap;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author Carl
 * version 1.0
 * @since 2018/6/10
 */
public interface RestAPI {
    @POST("UserInterface.aspx")
    Flowable<JsonObject> login(@QueryMap TreeMap<String, String> map);

    @POST("sms.php")
    Flowable<SmsBean> getSms(@QueryMap TreeMap<String, String> map);

    @GET("h_register.php")
    Flowable<JsonObject> register(@QueryMap TreeMap<String, String> map);


}
