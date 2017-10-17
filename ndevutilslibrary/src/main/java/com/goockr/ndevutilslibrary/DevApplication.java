package com.goockr.ndevutilslibrary;

import android.app.Application;

import com.goockr.ndevutilslibrary.fils.SharedPreferencesUtils;
import com.goockr.ndevutilslibrary.tools.CrashUtil;

/**
 * Created by LJN on 2017/10/17.
 */

public class DevApplication extends Application {
    public SharedPreferencesUtils preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        new CrashUtil().init(this);//初始化异常类
        preferences = SharedPreferencesUtils.getInstance(this);//初始化preference
    }
}
