package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.widget.EditText;
import android.widget.TextView;

public class MedicalSuggestions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_suggestions);
        TextView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> exitFunction());
       // EditText search = findViewById(R.id.search);

        //Temporary
        TextView regButton = findViewById(R.id.statistics);
        regButton.setOnClickListener(view -> statFunction());
    }
    public void exitFunction(){
        finish();
    }
    public void statFunction(){
        Intent send = new Intent(MedicalSuggestions.this, Statistics.class);
        startActivity(send);
    }

}