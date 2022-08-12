package com.huawei.batch6.pillstime;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import android.widget.EditText;

public class MedicalSuggestions extends AppCompatActivity {

    //Back Button
    private TextView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_suggestions);

        //Back Button Event handling
        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> backFunction());
    }

    //Back Functionality
    public void backFunction() {
        finish();
    }


}