package com.niluogege.plugin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.niluogege.plugin.plugin.PluginAppCompatActivity;

public class MainActivity extends PluginAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView iv = findViewById(R.id.iv);

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
                Intent intent = new Intent();
                intent.setClassName("com.niluogege.plugindemo", "com.niluogege.plugindemo.HostActivity2");
                startActivity(intent);
            }
        });

        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过 getIdentifier 需要设置为 插件的包名，才能正确获取到 资源
                int identifier = getResources().getIdentifier("huabei", "mipmap", "com.niluogege.plugin");
                //通过 getPackageName 方法获取到的是 宿主的 包名 ，所以不能直接用api获取
                String packageName = getPackageName();
                Log.e("MainActivity", "插件中 getPackageName()= " + packageName);
                iv.setImageResource(identifier);
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
