package com.niluogege.plugin.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by niluogege on 2020/4/20.
 */
public class PluginActivity extends Activity {

    @Override
    protected void attachBaseContext(Context newBase) {

        try {
            //去宿主中找到 com.niluogege.plugindemo.MyPlugin 这个类
            Class<?> forName = Class.forName("com.niluogege.plugindemo.plugin.MyPlugin", false, Entry.hostCL);
            Method createActivityContext = forName.getDeclaredMethod("createActivityContext", Activity.class, Context.class);
            createActivityContext.setAccessible(true);
            newBase = (Context) createActivityContext.invoke(null, this, newBase);
            Log.d("PluginActivity", "newBase:" + newBase);
            //替换插件Activity用的 mBase
            super.attachBaseContext(newBase);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void startActivity(Intent intent) {
        Log.e("PluginActivity", "startActivity");
        try {
            Class<?> forName = Class.forName("com.niluogege.plugindemo.plugin.MyPlugin", false, Entry.hostCL);
            Method startActivity = forName.getDeclaredMethod("startActivity", Activity.class, Intent.class);
            startActivity.setAccessible(true);
            //是否是替换操作
            boolean isReplace = (boolean) startActivity.invoke(null, this, intent);
            Log.e("PluginActivity", "startActivity isReplaced= " + isReplace);
            if (isReplace) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.startActivity(intent);
    }
}
