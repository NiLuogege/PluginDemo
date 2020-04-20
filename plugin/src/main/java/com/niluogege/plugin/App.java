package com.niluogege.debug;

import android.app.Application;
import android.util.Log;

/**
 * Created by niluogege on 2020/4/20.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("App", "插件的Application 起来啦");
    }
}
