package com.niluogege.plugindemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niluogege on 2020/4/20.
 */
public class MyPlugin {
    public static final Map<String, String> activityMap = new HashMap<>();
    public static final String CONTENNERACT = "com.niluogege.plugindemo.PlaceholderActivity";

    /**
     * @param pluginPkg 插件包名
     * @param aCls      要打卡的插件中的Activity
     * @return
     */
    public static Intent createIntent(String pluginPkg, String aCls) {

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.niluogege.plugindemo", CONTENNERACT));
        intent.addCategory("pluginPkg:" + pluginPkg);
        intent.addCategory("activity:" + aCls);


        activityMap.put(CONTENNERACT, aCls);

        return intent;
    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }
}
