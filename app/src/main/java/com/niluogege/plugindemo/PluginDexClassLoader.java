package com.niluogege.plugindemo;

import android.util.Log;

import dalvik.system.DexClassLoader;

/**
 * Created by niluogege on 2020/4/20.
 */
public class PluginDexClassLoader extends DexClassLoader {
    public PluginDexClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);

        Log.d("PluginDexClassLoader", toString());
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {


        return super.loadClass(name, resolve);
    }
}
