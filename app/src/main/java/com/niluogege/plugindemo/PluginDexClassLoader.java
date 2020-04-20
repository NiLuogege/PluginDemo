package com.niluogege.plugindemo;

import android.util.Log;

import dalvik.system.DexClassLoader;

/**
 * Created by niluogege on 2020/4/20.
 */
public class PluginDexClassLoader extends DexClassLoader {

    private final ClassLoader clearCl;

    public PluginDexClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);

        clearCl = App.instance.getClassLoader();

        Log.d("PluginDexClassLoader", toString());
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        Log.d("PluginDexClassLoader", name);

        Class<?> aClass = super.loadClass(name, resolve);

        if (aClass == null) {
            aClass = clearCl.loadClass(name);
        }

        return aClass;
    }
}
