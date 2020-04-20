package com.niluogege.plugindemo;

import dalvik.system.DexClassLoader;

/**
 * Created by niluogege on 2020/4/20.
 */
public class PluginDexClassLoader extends DexClassLoader {
    public PluginDexClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
    }
}
