package com.carl.mvpdemo.module.home.model;

import com.carl.mvpdemo.pub.network.HttpClient;
import com.carl.mvpdemo.pub.network.RestAPI;
import com.google.gson.JsonObject;

import java.util.TreeMap;

import io.reactivex.Flowable;

public class YiMaDataCenter {
    private static final YiMaDataCenter ourInstance = new YiMaDataCenter();
    private static RestAPI service;

    public static YiMaDataCenter getInstance() {
        return ourInstance;
    }

    private YiMaDataCenter() {
        service = HttpClient.getInstance("http://api.fxhyd.cn/").service;
    }

    public Flowable<JsonObject> login(String username, String password) {
        String ACTION = "preLogin";
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("action", ACTION);
        treeMap.put("username", username);
        treeMap.put("password", password);
        return service.login(treeMap);
    }


}
