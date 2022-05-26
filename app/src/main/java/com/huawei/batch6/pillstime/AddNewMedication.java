package com.huawei.batch6.pillstime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewMedication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_medication);

        TextView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> backFunction());

        TextView nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(view -> nextFunction());

        TextView medicalSuggestion = findViewById(R.id.medicalSuggestions);
        medicalSuggestion.setOnClickListener(view -> medFunction());

    }

    public void backFunction(){
        finish();
    }

    public void nextFunction(){
        Intent send = new Intent(AddNewMedication.this,AddDose.class);
        startActivity(send);
    }

    public void medFunction(){
        Intent send = new Intent(AddNewMedication.this,MedicalSuggestions.class);
        startActivity(send);
    }
}