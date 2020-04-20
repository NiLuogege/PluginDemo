package com.niluogege.plugindemo;

import android.content.Context;

import com.niluogege.plugindemo.utils.FileUtils;

import java.io.File;
import java.io.InputStream;

/**
 * Created by niluogege on 2020/4/20.
 */
public class Plugin {
    //插件使用的classLoader
    public ClassLoader pluginCl;

    private static Context context;


    public static void init(App app) {
        context = app;
    }


    public static Plugin getPlugin(String name) {
        return null;
    }

    public static void installPlugin() {
        //拷贝插件到沙盒中
        copyPluginToSandbox();

        //


    }

    private static void copyPluginToSandbox() {
        try {
            //私有目录下的 pluginDir
            File pluginDir = context.getDir("plugin", Context.MODE_PRIVATE);
            // assets 文件夹下的 插件
            InputStream is = context.getAssets().open("plugin.apk");

            if (!pluginDir.exists() || pluginDir.isFile()) {
                pluginDir.mkdirs();
            }

            //创建插件对象
            File plugin = new File(pluginDir, "plugin.apk");

            //拷贝到私有目录
            FileUtils.copyInputStreamToFile(is, plugin);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
