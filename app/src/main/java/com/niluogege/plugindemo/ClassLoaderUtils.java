package com.niluogege.plugindemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by niluogege on 2020/4/20.
 */
public class ClassLoaderUtils {
    public static void hookPathCL(Application app) {
        try {
            Context baseContext = app.getBaseContext();
            // LoadedApk
            Object mPackageInfo = ReflectUtils.readField(baseContext, "mPackageInfo");

            //ClassLoader
            ClassLoader originCl = (ClassLoader) ReflectUtils.readField(mPackageInfo, "mClassLoader");

            //植入自己的 ClassLoader
            ReflectUtils.writeField(mPackageInfo,"mClassLoader",new MyPathClassLoader(originCl));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
