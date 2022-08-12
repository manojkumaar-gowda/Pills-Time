package com.huawei.batch6.pillstime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewMedication extends AppCompatActivity {


    //Back Button
    private TextView backButton;

    //Next Button
    private TextView nextButton;

    //Medical Suggestions
    private TextView medicalSuggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_medication);

        //back button event handling
        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> backFunction());

        //next button event handling
        nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(view -> nextFunction());

        //medicalSuggestions button event handling
        medicalSuggestion = findViewById(R.id.medicalSuggestions);
        medicalSuggestion.setOnClickListener(view -> medicalSuggestionFunctionality());

    }

    //Back Button Functionality
    public void backFunction() {
        finish();
    }

    //Next Button Functionality
    public void nextFunction() {
        Intent send = new Intent(AddNewMedication.this, AddDose.class);
        startActivity(send);
    }

    //Medical Suggestions Button Functionality
    public void medicalSuggestionFunctionality() {
        Intent send = new Intent(AddNewMedication.this, MedicalSuggestions.class);
        startActivity(send);
    }
}