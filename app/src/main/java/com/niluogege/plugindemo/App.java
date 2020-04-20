package com.niluogege.plugindemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by niluogege on 2020/4/20.
 */
public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //hook classLoader
        ClassLoaderUtils.hookPathCL(this);
        //安装 插件
        PluginUtils.installPlugin();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
