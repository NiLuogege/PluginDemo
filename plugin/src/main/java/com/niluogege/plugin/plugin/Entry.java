package com.niluogege.plugin.plugin;

import android.content.Context;

/**
 * Created by niluogege on 2020/4/20.
 */
public class Entry {
    public static Context pluginContext = null;
    public static ClassLoader hostCL = null;


    /**
     * @param context 插件 context
     * @param loader  宿主  类加载器
     */
    public static final void create(Context context, ClassLoader loader) {
        pluginContext = context;
        hostCL = loader;
    }

}
