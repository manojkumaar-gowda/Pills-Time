package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView exitButton = findViewById(R.id.exit);
        exitButton.setOnClickListener(view -> exitFunction());
        TextView regButton = findViewById(R.id.reg);
        regButton.setOnClickListener(view -> regFunction());
    }
    public void exitFunction(){
        finish();
        System.exit(0);
    }

    public void regFunction(){
        Intent send = new Intent(LoginActivity.this, MedicalSuggestions.class);
        startActivity(send);
    }
}