package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AccountRegistration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    String[] heights = { "cm", "m", "feet"};

    String[] weights = { "kg", "pound"};

    String[] gender = { "Male", "Female", "Prefer not to say"};


    TextView pickDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_registration);

        TextView logoutButton = findViewById(R.id.log_out);
        logoutButton.setOnClickListener(view -> logoutFunction());

        TextView accButton = findViewById(R.id.dash);
        accButton.setOnClickListener(view -> accFunction());



//heights
        Spinner height_spino = findViewById(R.id.height_units);
        ArrayAdapter ad
                = new ArrayAdapter(this, android.R.layout.simple_spinner_item, heights);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        height_spino.setAdapter(ad);

//weight
        Spinner weight_spino = findViewById(R.id.weight_units);
        ArrayAdapter ad1
                = new ArrayAdapter(this, android.R.layout.simple_spinner_item, weights);
        ad1.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        weight_spino.setAdapter(ad1);

//gender
        Spinner gender_spino = findViewById(R.id.gender);
        ArrayAdapter ad2
                = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        ad2.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        gender_spino.setAdapter(ad2);


//datePicker
        pickDate = findViewById(R.id.dob);
        //set listener on button click
        pickDate.setOnClickListener(view -> showDatePickerDialog());

    }

    public void logoutFunction(){
        finish();

    }

    public void accFunction(){
        Intent send = new Intent(AccountRegistration.this,MainActivity.class);
        startActivity(send);
    }

    private void showDatePickerDialog(){
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

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int date){
        //set the date
        String text = date+"/"+(month+1)+"/"+year;
        pickDate.setText(text);
    }
}