package com.niluogege.plugindemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by niluogege on 2020/4/20.
 */
public class App extends Application {
    public static App instance = null;
    public  Plugin plugin;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        instance = this;

        //hook classLoader
        ClassLoaderUtils.hookPathCL(this);
        //安装 插件
        Plugin plugin = new Plugin(this);
        plugin.installPlugin();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
