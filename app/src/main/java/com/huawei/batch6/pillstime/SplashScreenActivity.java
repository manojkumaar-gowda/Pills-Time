package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//Redirects to the login Page after 2.3 seconds
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent send = new Intent(getApplicationContext(),
                    MainActivity.class);
            startActivity(send);
            finish();
        }, 2300);
    }
}