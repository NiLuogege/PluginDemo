package com.niluogege.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.niluogege.plugin.plugin.PluginActivity;

/**
 * Created by niluogege on 2020/4/20.
 */
public class Demo2Activity extends PluginActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo1);

        Log.e("Demo2Activity", "Demo2Activity");
    }
}
