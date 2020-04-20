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
    public ClassLoader pluginCl;//插件的classLoader

    private Context context;
    private String mPath;//apk 在沙盒中的存储路径

    public Plugin(App app) {
        context = app;
        //拷贝插件到沙盒中
        copyPluginToSandbox();
        //插件存放路径
        File pluginDir = getPluginDir();
        //优化后dex路径
        String optimizedDirectory = new File(pluginDir, "odex").getAbsolutePath();
        //so包路径
        String librarySearchPath = new File(pluginDir, "so").getAbsolutePath();
        ClassLoader parentCl = getClass().getClassLoader().getParent();
        pluginCl = new PluginDexClassLoader(mPath, optimizedDirectory, librarySearchPath, parentCl);
    }


    public static Plugin getPlugin(String name) {
        return null;
    }

    public void installPlugin() {

        //

        //加载插件的Application

    }

    private void copyPluginToSandbox() {
        try {
            //私有目录下的 pluginDir
            File pluginDir = getPluginDir();
            // assets 文件夹下的 插件
            InputStream is = context.getAssets().open("plugin.apk");

            if (!pluginDir.exists() || pluginDir.isFile()) {
                pluginDir.mkdirs();
            }

            //创建插件对象
            File plugin = new File(pluginDir, "plugin.apk");

            mPath = plugin.getAbsolutePath();

            //拷贝到私有目录
            FileUtils.copyInputStreamToFile(is, plugin);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File getPluginDir() {
        return context.getDir("plugin", Context.MODE_PRIVATE);
    }


}
