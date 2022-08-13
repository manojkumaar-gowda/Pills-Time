package com.huawei.batch6.pillstime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewMedication extends AppCompatActivity {


    //Back Button
    private TextView backButton;

    //Next Button
    private TextView nextButton;

    //Medical Suggestions
    private TextView medicalSuggestion;

    //input fields
    private EditText nameOfTheMedicine;
    private EditText distributor;
    private EditText medicalHistory;
    private EditText recommendedBy;

    //scan_and_fetch_details_button
    private Button scan_and_fetch_details_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_medication);

        //link inputs
        linkInputs();


        //scan_and_fetch_details_button event handler
        scan_and_fetch_details_button = findViewById(R.id.scan_and_fetch_details_button);
        scan_and_fetch_details_button.setOnClickListener(v -> scan_and_fetch_details_button_Function());

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

    //link inputs
    private void linkInputs() {
        nameOfTheMedicine = findViewById(R.id.name_of_the_medication);
        distributor = findViewById(R.id.distributor);
        medicalHistory = findViewById(R.id.medicalHistory);
        recommendedBy = findViewById(R.id.recommendedBy);
    }

    //scan and fetch details functionality
    private void scan_and_fetch_details_button_Function() {
        nameOfTheMedicine.setText("Prograf");
        nameOfTheMedicine.setError(null);
        distributor.setText("Biocon");
        distributor.setError(null);

        //temporary
        medicalHistory.setText("Transplant");
        medicalHistory.setError(null);
        recommendedBy.setText("Dr.RCV");
        recommendedBy.setError(null);

    }

    //Back Button Functionality
    public void backFunction() {
        finish();
    }

    //Next Button Functionality
    public void nextFunction() {
        //get all input values
        String nameOfTheMedicineValue = nameOfTheMedicine.getText().toString();
        String distributorValue = distributor.getText().toString();
        String medicalHistoryValue = medicalHistory.getText().toString();
        String recommendedByValue = recommendedBy.getText().toString();

        //check for validity
        //assuming that no validation errors exist
        boolean noVaidationErrors = true;

        //nameOfTheMedicine
        if (nameOfTheMedicineValue.equals("")) {
            nameOfTheMedicine.setError("Name of the medicine cannot be empty");
            noVaidationErrors = false;
        } else {
            nameOfTheMedicine.setError(null);
        }

        //distributorValue
        if (distributorValue.equals("")) {
            distributor.setError("Distributor cannot be empty");
            noVaidationErrors = false;
        } else {
            distributor.setError(null);
        }

        //medicalHistoryValue
        if (medicalHistoryValue.equals("")) {
            medicalHistory.setError("Medical History cannot be empty");
            noVaidationErrors = false;
        } else {
            medicalHistory.setError(null);
        }

        //recommendedByValue
        if (recommendedByValue.equals("")) {
            recommendedByValue = "Not Specified";
        }

        if (noVaidationErrors) {
            Intent send = new Intent(AddNewMedication.this, AddDose.class);
            Bundle bundle = new Bundle();
            bundle.putString("nameOfTheMedication", nameOfTheMedicineValue);
            bundle.putString("distributor", distributorValue);
            bundle.putString("medicalHistory", medicalHistoryValue);
            bundle.putString("recommendedBy", recommendedByValue);
            send.putExtras(bundle);
            startActivity(send);
        }
    }

    //Medical Suggestions Button Functionality
    public void medicalSuggestionFunctionality() {
        Intent send = new Intent(AddNewMedication.this, MedicalSuggestions.class);
        startActivity(send);
    }
}