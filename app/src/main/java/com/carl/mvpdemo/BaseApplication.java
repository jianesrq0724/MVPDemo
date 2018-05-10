package com.carl.mvpdemo;

import android.app.Application;
import android.content.Context;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
