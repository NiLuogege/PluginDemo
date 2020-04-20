package com.niluogege.debug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Demo1Activity.class));
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
