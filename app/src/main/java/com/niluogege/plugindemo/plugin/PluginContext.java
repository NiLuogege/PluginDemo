package com.niluogege.plugindemo.plugin;

import android.content.res.Resources;
import android.view.ContextThemeWrapper;

/**
 * Created by niluogege on 2020/4/20.
 */
public class PluginContext extends ContextThemeWrapper {
    private final Resources mResources;

    public PluginContext(Resources mResources) {
        this.mResources = mResources;
    }

    @Override
    public Resources getResources() {
        if (mResources != null) {
            return mResources;
        }
        return super.getResources();
    }
}
