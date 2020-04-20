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
}
