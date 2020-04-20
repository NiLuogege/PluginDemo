package com.niluogege.plugindemo;

import dalvik.system.PathClassLoader;

/**
 * Created by niluogege on 2020/4/20.
 */
public class MyPathClassLoader extends PathClassLoader {

    private ClassLoader originCl;

    public MyPathClassLoader(ClassLoader originCl) {
        super("", "", originCl.getParent());
        this.originCl = originCl;
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        try {
            //先从插件中找
            Class<?> aClass = Plugin.getPlugin(name).pluginCl.loadClass(name);
            if (aClass == null) {
                //再从之前的中找
                aClass = originCl.loadClass(name);
                if (aClass != null) {
                    return aClass;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //最后从自己身上找
        return super.loadClass(name, resolve);
    }
}
