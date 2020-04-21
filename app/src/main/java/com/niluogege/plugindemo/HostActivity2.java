package com.niluogege.plugindemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by niluogege on 2020/4/21.
 */
public class HostActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_host_2);
        findViewById(R.id.btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HostActivity2.this, "我是HostActivity2", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
