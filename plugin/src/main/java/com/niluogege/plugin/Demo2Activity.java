package com.niluogege.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        Button tv = findViewById(R.id.tv);
        tv.setText("我是Demo2Activity， 点我！");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Demo2Activity.this, "Demo2Activity", Toast.LENGTH_LONG).show();
            }
        });

        Log.e("Demo2Activity", "Demo2Activity");
    }
}
