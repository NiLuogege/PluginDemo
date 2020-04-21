package com.niluogege.plugin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.niluogege.plugin.plugin.PluginAppCompatActivity;

public class MainActivity extends PluginAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Demo1Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //因为我们设置的classLoader 会在 插件中找不到的手 去 宿主中找，所以我们 直接 打开就行了
                Intent intent = new Intent();
                intent.setClassName("com.niluogege.plugindemo", "com.niluogege.plugindemo.HostActivity2");
                startActivity(intent);
            }
        });

        Context context = findViewById(R.id.tv).getContext();

        Context applicationContext = getApplicationContext();
        Context AppBaseContext = getApplication().getBaseContext();


        Context activityBaseContext = getBaseContext();


        //context:com.niluogege.debug.MainActivity@5c430b0
        // applicationContext= android.app.App@253346c
        // AppBaseContext= android.app.ContextImpl@eee6a35
        // activityBaseContext= android.app.ContextImpl@9dd8eca
        Log.e("插件 MainActivity", "context:" + context + " applicationContext= " + applicationContext
                + " AppBaseContext= " + AppBaseContext + " activityBaseContext= " + activityBaseContext);

    }
}
