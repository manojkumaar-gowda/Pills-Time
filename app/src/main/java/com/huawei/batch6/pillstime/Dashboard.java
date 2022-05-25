package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView regButton = findViewById(R.id.medical_sugestions);
        regButton.setOnClickListener(view -> regFunction());
    }
    public void regFunction(){
        Intent send = new Intent(Dashboard.this, MedicalSuggestions.class);
        startActivity(send);
    }
}