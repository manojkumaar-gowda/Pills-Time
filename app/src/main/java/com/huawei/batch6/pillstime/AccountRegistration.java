package com.huawei.batch6.pillstime;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AccountRegistration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    //Spinner Options
    private final String[] heights = {"cm", "m", "feet"};
    private final String[] weights = {"kg", "pound"};
    private final String[] gender = {"Male", "Female", "Prefer not to say"};

    //Date Pickers
    private TextView pickDate;
    //Logout
    private TextView logoutButton;
    //Temporary way to dashboard
    private TextView accButton;

    //Spinners
    private Spinner height_spinner;
    private Spinner weight_spinner;
    private Spinner gender_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_registration);

        //Logout event handling
        logoutButton = findViewById(R.id.log_out);
        logoutButton.setOnClickListener(view -> logoutFunction());

        //Temporary way to dashboard
        accButton = findViewById(R.id.dash);
        accButton.setOnClickListener(view -> accFunction());


        //Set heights for spinner
        height_spinner = findViewById(R.id.height_units);
        ArrayAdapter heightArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, heights);
        heightArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        height_spinner.setAdapter(heightArrayAdapter);

        //Set weights for spinner
        weight_spinner = findViewById(R.id.weight_units);
        ArrayAdapter weightArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, weights);
        weightArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weight_spinner.setAdapter(weightArrayAdapter);

        //Set gender for spinner
        gender_spinner = findViewById(R.id.gender);
        ArrayAdapter genderArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        genderArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(genderArrayAdapter);


        //datePicker
        //Button click event handler
        pickDate = findViewById(R.id.dob);
        pickDate.setOnClickListener(view -> showDatePickerDialog());

    }

    //Logout functionality
    public void logoutFunction() {
        finish();

    }

    //Temporary door to dashboard functionality
    public void accFunction() {
        Intent send = new Intent(AccountRegistration.this, MainActivity.class);
        startActivity(send);
    }


    //Date Picker functionality
    private void showDatePickerDialog() {
        //create a new DatePickerDialog object and set the default selected date to present dat
        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        //display the datePicker
        datePicker.show();
    }

    //Set date to the input field functionality
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
        //set the date
        String text = date + "/" + (month + 1) + "/" + year;
        pickDate.setText(text);
    }
}