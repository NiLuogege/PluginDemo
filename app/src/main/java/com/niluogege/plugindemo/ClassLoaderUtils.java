package com.niluogege.plugindemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by niluogege on 2020/4/20.
 */
public class ClassLoaderUtils {

    //宿主CL
    private static MyPathClassLoader hostCL;


    public static void hookPathCL(Application app) {
        try {
            Context baseContext = app.getBaseContext();
            // LoadedApk
            Object mPackageInfo = ReflectUtils.readField(baseContext, "mPackageInfo");

            //ClassLoader
            ClassLoader originCl = (ClassLoader) ReflectUtils.readField(mPackageInfo, "mClassLoader");

            //宿主CL
            hostCL = new MyPathClassLoader(originCl);

            //植入自己的 ClassLoader
            ReflectUtils.writeField(mPackageInfo, "mClassLoader", hostCL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ClassLoader getHostCL() {
        return hostCL;
    }
}
