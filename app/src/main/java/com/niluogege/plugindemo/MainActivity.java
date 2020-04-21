package com.niluogege.plugindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.niluogege.plugindemo.plugin.MyPlugin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MyPlugin.createIntent("com.niluogege.plugin", "com.niluogege.plugin.MainActivity");
                startActivity(intent);

            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MyPlugin.createIntent("com.niluogege.plugin", "com.niluogege.plugin.Demo2Activity");
                startActivity(intent);

            }
        });
    }
}
