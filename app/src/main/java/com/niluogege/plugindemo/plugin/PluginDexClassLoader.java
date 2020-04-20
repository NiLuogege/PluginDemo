package com.niluogege.plugindemo.plugin;

import android.util.Log;

import com.niluogege.plugindemo.App;

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
    protected Class<?> loadClass(String name, boolean resolve) {
        Class<?> aClass = null;
        try {
            Log.d("PluginDexClassLoader", name);

            aClass = super.loadClass(name, resolve);

            if (aClass == null) {
                aClass = clearCl.loadClass(name);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return aClass;
    }
}
