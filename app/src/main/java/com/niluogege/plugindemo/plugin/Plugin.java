package com.niluogege.plugindemo.plugin;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import com.niluogege.plugindemo.App;
import com.niluogege.plugindemo.utils.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Created by niluogege on 2020/4/20.
 */
public class Plugin {
    //插件使用的classLoader
    public static ClassLoader pluginCl;//插件的classLoader

    private Context context;//宿主APPliction
    private String mPath;//apk 在沙盒中的存储路径
    Application mApplication;
    //插件使用的 context
    private PluginContext pluginContext;
    //插件中解析出来的 Resources
    private Resources mResources;

    public Plugin(App app) {
        try {
            context = app;
            //拷贝插件到沙盒中
            copyPluginToSandbox();
            //插件存放路径
            File pluginDir = getPluginDir();

            //优化后dex路径
            File optimizedDirectory = new File(pluginDir, "odex");
            if (!optimizedDirectory.exists() || optimizedDirectory.isFile())
                optimizedDirectory.mkdirs();

            //so包路径
            File librarySearchPath = new File(pluginDir, "so");
            if (!librarySearchPath.exists() || librarySearchPath.isFile())
                librarySearchPath.mkdirs();


            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageArchiveInfo(mPath, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES | PackageManager.GET_PROVIDERS | PackageManager.GET_RECEIVERS | PackageManager.GET_META_DATA);
            packageInfo.applicationInfo.sourceDir = mPath;
            packageInfo.applicationInfo.publicSourceDir = mPath;

//        if (TextUtils.isEmpty(packageInfo.applicationInfo.processName)) {
//            packageInfo.applicationInfo.processName = packageInfo.applicationInfo.packageName;
//        }

            packageInfo.applicationInfo.nativeLibraryDir = librarySearchPath.getAbsolutePath();

            Resources r = pm.getResourcesForApplication(packageInfo.applicationInfo);
            mResources = new Resources(r.getAssets(), r.getDisplayMetrics(), r.getConfiguration());


            ClassLoader parentCl = getClass().getClassLoader().getParent();
            pluginCl = new PluginDexClassLoader(mPath, optimizedDirectory.getAbsolutePath(), librarySearchPath.getAbsolutePath(), parentCl);

            pluginContext = new PluginContext(context,mResources);


            //初始化插件入口
            initPluginEntry();


            //加载 插件 Appliction
            callApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPluginEntry() {
        try {
            Class<?> aClass = pluginCl.loadClass("com.niluogege.plugin.plugin.Entry");
            Method create = aClass.getDeclaredMethod("create", Context.class, ClassLoader.class);
            create.invoke(null, pluginContext, ClassLoaderUtils.getHostCL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callApp() {
        try {
            Class<?> clazz = pluginCl.loadClass("com.niluogege.plugin.App");
            mApplication = (Application) clazz.newInstance();

            // NOTE getDeclaredMethod只能获取当前类声明的方法，无法获取继承到的，而getMethod虽可以获取继承方法，但又不能获取非Public的方法
            // NOTE 权衡利弊，还是仅构造函数用反射类，其余用它明确声明的类来做
            //调用 attach
            Method attach = Application.class.getDeclaredMethod("attach", Context.class);
            attach.setAccessible(true);
            attach.invoke(mApplication, context);

            //调用 onCreate
            mApplication.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Class<?> loadClass(String name, boolean resolve) {
        Class<?> c = null;
        try {

            Log.d("Plugin", "name= :" + name + " bool= " + MyPlugin.CONTENNERACT.equals(name));

            if (MyPlugin.CONTENNERACT.equals(name)) {
                String realClass = MyPlugin.activityMap.get(name);
                c = pluginCl.loadClass(realClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
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
            InputStream is = context.getAssets().open("plugin-release.apk");

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


    public Context createActivityContext() {
        return new PluginContext(context,mResources);
    }
}
