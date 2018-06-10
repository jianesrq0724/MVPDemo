package com.carl.mvpdemo.module.home.model;

import com.carl.mvpdemo.module.home.bean.SmsBean;
import com.carl.mvpdemo.pub.network.HttpClient;
import com.carl.mvpdemo.pub.network.RestAPI;
import com.google.gson.JsonObject;

import java.util.TreeMap;

import io.reactivex.Flowable;

public class HuLianDataCenter {
    private static RestAPI service;

    private static final HuLianDataCenter ourInstance = new HuLianDataCenter();

    public static HuLianDataCenter getInstance() {
        return ourInstance;
    }

    private HuLianDataCenter() {
        service = HttpClient.getInstance("https://www.chainhoo.com/interface/").service;
    }

    public Flowable<JsonObject> register(String phone, String code) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("type", "bind");
        treeMap.put("phone", phone);
        treeMap.put("code", code);
        treeMap.put("uid", "296846");
        return service.register(treeMap);
    }

    public Flowable<SmsBean> getSms(String phone) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("phone", phone);
        return service.getSms(treeMap);
    }


}
