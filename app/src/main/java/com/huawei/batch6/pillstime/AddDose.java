package com.huawei.batch6.pillstime;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;



public class AddDose extends AppCompatActivity implements AdapterView.OnItemSelectedListener , DatePickerDialog.OnDateSetListener{

    //private Spinner units;
    String[] doseUnits = {"Units", "mg", "ml"};
    String[] dayTypes = {"Everyday","Specific Days of week","Custom days"};
    List<String> timeList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dose);

        //Setting spinner items for dose
        final Spinner units = (Spinner) findViewById(R.id.dose_units);
        final List<String> doselist = new ArrayList<>(Arrays.asList(doseUnits));
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, doselist) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        units.setAdapter(spinnerArrayAdapter);


        //dayType selector
        // Spinner Drop down elements
        final Spinner dayTypeSpinner = (Spinner) findViewById(R.id.dayTypeSpinner);
        final List<String> categories = new ArrayList<>(Arrays.asList(dayTypes));

        // Creating adapter for spinner

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        // attaching data adapter to spinner
        dayTypeSpinner.setAdapter(dataAdapter);
        // Spinner click listener
        dayTypeSpinner.setOnItemSelectedListener(this);


        //back button
        TextView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> backFunction());

        //Finish button
        TextView finishButton = findViewById(R.id.finish);
        finishButton.setOnClickListener(view -> finishFunction());

        TextView editTextTimer = findViewById(R.id.time);
        editTextTimer.setOnClickListener(v -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;

            mTimePicker = new TimePickerDialog(AddDose.this, (timePicker, selectedHour, selectedMinute) -> {
                String dayOrNight = "AM";

                @SuppressLint("DefaultLocale") String minutes = String.format("%02d",selectedMinute);
                if(selectedHour > 12){
                    dayOrNight = "PM";
                    selectedHour-=12;
                }
                else if(selectedHour == 0){
                    selectedHour = 12;
                }
                else if(selectedHour == 12){
                    dayOrNight = "PM";
                }
                @SuppressLint("DefaultLocale") String hours = String.format("%02d",selectedHour);
                String timeEntered = hours + ":" + minutes+" "+dayOrNight;
                editTextTimer.setText( timeEntered);
                editTextTimer.requestFocus();
            }, hour, minute,  false);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");

            mTimePicker.show();
        });



        Button addDoseButton = findViewById(R.id.add_time);
        addDoseButton.setOnClickListener(v -> {
            TextView timer = findViewById(R.id.time);
            String t = timer.getText().toString();
            if(!t.equals("Click to select time")){
                if(timeList.contains(t)){
                    timer.setText("Click to select time");
                    return;
                }
                timeList.add(t);
                timer.setText("Click to select time");
                StringBuilder timesString = new StringBuilder();
                for(String time:timeList){
                    timesString.append(" ~ ").append(time);
                }
                timesString.delete(0,3);
                TextView timeList = findViewById(R.id.timeList);
                timeList.setText(timesString);
                Toast.makeText(getApplicationContext(), timesString, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Select time", Toast.LENGTH_SHORT).show();
            }

        });

        TextView from = findViewById(R.id.from);
        //set listener on button click
        from.setOnClickListener(view -> showDatePickerDialog());

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        ca.antonious.materialdaypicker.MaterialDayPicker specificDay = findViewById(R.id.enabledDaysSelector);
        LinearLayout customeDays = findViewById(R.id.customDays);
        switch (item) {
            case "Specific Days of week":
                //Toast.makeText(getApplicationContext(), "Specific Days of week", Toast.LENGTH_SHORT).show();
                specificDay.clearSelection();
                specificDay.setVisibility(ca.antonious.materialdaypicker.MaterialDayPicker.VISIBLE);
                customeDays.setVisibility(View.GONE);
                break;
            case "Everyday":
                //Toast.makeText(getApplicationContext(), "EVERYDAY", Toast.LENGTH_SHORT).show();
                specificDay.selectAllDays();
                specificDay.setVisibility(ca.antonious.materialdaypicker.MaterialDayPicker.VISIBLE);
                customeDays.setVisibility(View.GONE);
                break;
            case "Custom days":
                //Toast.makeText(getApplicationContext(), "Custom Day", Toast.LENGTH_SHORT).show();
                specificDay.setVisibility(ca.antonious.materialdaypicker.MaterialDayPicker.GONE);
                customeDays.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void backFunction(){
        onBackPressed();
    }

    public void finishFunction(){
        Intent send = new Intent(AddDose.this,MainActivity.class);
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
        TextView from = findViewById(R.id.from);
        String text = "Starting from "+date+"/"+(month+1)+"/"+year;
        from.setText(text);
    }


    @Override
    public void onBackPressed() {
        if(!timeList.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.pills_time_logo)
                    .setTitle("Pills-Time")
                    .setMessage("Unsaved changes. Are you sure you want to discard changes and go back?")
                    .setPositiveButton("Yes", (dialog, which) -> finish())
                    .setNegativeButton("No", null)
                    .show();
        }else{
            finish();
        }
    }

}
