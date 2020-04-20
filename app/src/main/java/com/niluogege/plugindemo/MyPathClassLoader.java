package com.niluogege.plugindemo;

import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;

import dalvik.system.PathClassLoader;

/**
 * Created by niluogege on 2020/4/20.
 */
public class MyPathClassLoader extends PathClassLoader {

    private ClassLoader originCl;

    public MyPathClassLoader(ClassLoader originCl) {
        super("", "", originCl.getParent());
        this.originCl = originCl;
        copyFromOriginal(originCl);
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        Log.e("MyPathClassLoader", name);

        try {
            //先从插件中找
            Class<?> aClass = Plugin.loadClass(name, resolve);
            if (aClass == null) {
                //再从之前的中找
                aClass = originCl.loadClass(name);
                if (aClass != null) {
                    return aClass;
                }
            } else {
                return aClass;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //最后从自己身上找
        return super.loadClass(name, resolve);
    }


    /**
     * 将原来宿主里的关键字段，拷贝到这个对象上，这样骗系统以为用的还是以前的东西（尤其是DexPathList）
     * 注意，这里用的是“浅拷贝”
     *
     * @param orig
     */
    private void copyFromOriginal(ClassLoader orig) {

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {//小于 Android 2.3.3
            // Android 2.2 - 2.3.7，有一堆字段，需要逐一复制
            // 以下方法在较慢的手机上用时：8ms左右
            copyFieldValue("libPath", orig);
            copyFieldValue("libraryPathElements", orig);
            copyFieldValue("mDexs", orig);
            copyFieldValue("mFiles", orig);
            copyFieldValue("mPaths", orig);
            copyFieldValue("mZips", orig);
        } else {
            // Android 4.0以上只需要复制pathList即可
            // 以下方法在较慢的手机上用时：1ms
            copyFieldValue("pathList", orig);
        }
    }

    /**
     * 将 orig 中的 field字段拷贝到 this中来
     *
     * @param field
     * @param orig
     */
    private void copyFieldValue(String field, ClassLoader orig) {
        try {
            Field f = ReflectUtils.getField(orig.getClass(), field);
            if (f == null) {
                Log.d("MyPathClassLoader", "rpcl.cfv: null! f=" + field);
                return;
            }

            // 删除final修饰符
            ReflectUtils.removeFieldFinalModifier(f);

            // 复制Field中的值到this里
            Object o = ReflectUtils.readField(f, orig);
            ReflectUtils.writeField(f, this, o);

            Object test = ReflectUtils.readField(f, this);
            if (BuildConfig.DEBUG)
                Log.d("MyPathClassLoader", "copyFieldValue: Copied. f=" + field + "; actually=" + test + "; orig=" + o);
        } catch (IllegalAccessException e) {
            Log.d("MyPathClassLoader", "rpcl.cfv: fail! f=" + field);
        }
    }
}
