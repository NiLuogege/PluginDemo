package com.niluogege.plugindemo.plugin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.niluogege.plugindemo.App;

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


    public static Context createActivityContext(Activity activity, Context base) {
        return App.instance.plugin.createActivityContext();
    }

    /**
     * 提供给插件中 使用Activity打开插件中的Activity
     *
     * @param activity
     * @param intent
     */
    public static boolean startActivity(Activity activity, Intent intent) {
        ComponentName component = intent.getComponent();
        String packageName = component.getPackageName();
        String className = component.getClassName();
        Log.e("MyPlugin", " startActivity-> activity:" + activity + " PackageName= " + packageName + " ClassName= " + className + "startWith= " + className.startsWith(CONTENNERACT));

        if (className.startsWith(CONTENNERACT)) {//是否已经替换过
            return false;
        } else {
            intent = createIntent(packageName, className);
            activity.startActivity(intent);
            return true;
        }
    }

}
