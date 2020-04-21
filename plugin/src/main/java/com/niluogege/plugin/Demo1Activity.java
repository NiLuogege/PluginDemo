package com.niluogege.plugin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.niluogege.plugin.plugin.PluginAppCompatActivity;

/**
 * Created by niluogege on 2020/4/17.
 */
public class Demo1Activity extends PluginAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo1);

        Button tv = findViewById(R.id.tv);
        tv.setText("我是Demo1Activity， 点我！");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Demo1Activity.this, "Demo1Activity", Toast.LENGTH_LONG).show();
            }
        });

    }
}
