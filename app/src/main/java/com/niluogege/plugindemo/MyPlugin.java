package com.niluogege.plugindemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Created by niluogege on 2020/4/20.
 */
public class MyPlugin {
    /**
     * @param pluginPkg 插件包名
     * @param aCls      要打卡的插件中的Activity
     * @return
     */
    public static Intent createIntent(String pluginPkg, String aCls) {

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.niluogege.plugindemo", "com.niluogege.plugindemo.PlaceholderActivity"));
        intent.addCategory("pluginPkg:" + pluginPkg);
        intent.addCategory("activity:" + aCls);

        return intent;
    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }
}
