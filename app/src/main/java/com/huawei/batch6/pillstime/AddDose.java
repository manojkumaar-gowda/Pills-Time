package com.huawei.batch6.pillstime;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class AddDose extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    //Spinner Options
    private final String[] doseUnits = {"Units", "mg", "ml"};
    private final String[] dayTypes = {"Everyday", "Specific Days of week", "Custom days"};

    //List for time and dose models
    private final ArrayList<TimeModel> timeList = new ArrayList<>();
    private final ArrayList<AddDoseModel> addDoseList = new ArrayList<>();

    //Spinners
    private  Spinner units;
    private  Spinner dayTypeSpinner;

    //Back
    private TextView backButton;

    //Finish
    private TextView finishButton;

    //Time Dialog
    private TextView time_Picker;

    //DatePicker Dialog
    private TextView datePicker;

    //add Time Button
    private Button addTimeButton;

    //add Dose Button
    private Button addDoseButton;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dose);


        //Set units for spinner
        units = (Spinner) findViewById(R.id.dose_units);
        List<String> doselist = new ArrayList<>(Arrays.asList(doseUnits));
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, doselist) {
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


        //Set DayTypes for spinner
        dayTypeSpinner = (Spinner) findViewById(R.id.dayTypeSpinner);
        List<String> categories = new ArrayList<>(Arrays.asList(dayTypes));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        dayTypeSpinner.setAdapter(dataAdapter);
        //DayTypes Spinner onItemSelected listener
        dayTypeSpinner.setOnItemSelectedListener(this);


        //back button
        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> backFunction());

        //Finish  button
        finishButton = findViewById(R.id.finish);
        finishButton.setOnClickListener(view -> finishFunction());

        //Timer Dialog
        time_Picker = findViewById(R.id.time);
        time_Picker.setOnClickListener(v -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(AddDose.this, (timePicker, selectedHour, selectedMinute) -> {
                String dayOrNight = "AM";
                @SuppressLint("DefaultLocale") String minutes = String.format("%02d", selectedMinute);
                if (selectedHour > 12) {
                    dayOrNight = "PM";
                    selectedHour -= 12;
                } else if (selectedHour == 0) {
                    selectedHour = 12;
                } else if (selectedHour == 12) {
                    dayOrNight = "PM";
                }
                @SuppressLint("DefaultLocale") String hours = String.format("%02d", selectedHour);
                String timeEntered = hours + ":" + minutes + " " + dayOrNight;
                time_Picker.setText(timeEntered);
                time_Picker.requestFocus();
            }, hour, minute, false);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        });

        //Add time button
        addTimeButton = findViewById(R.id.add_time);
        addTimeButton.setOnClickListener(v -> {
            TextView timer = findViewById(R.id.time);
            String t = timer.getText().toString();
            //If time is selected
            if (!t.equals("Click to select time")) {
                for (TimeModel model : timeList) {
                    if (model.getTime().equals(t)) {
                        timer.setText("Click to select time");
                        return;
                    }
                }
                RecyclerView courseRV = findViewById(R.id.addedtimeRV);
                timeList.add(new TimeModel(t));
                TimeAdapter timeAdapter = new TimeAdapter(timeList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                courseRV.setLayoutManager(linearLayoutManager);
                courseRV.setAdapter(timeAdapter);
                timer.setText("Click to select time");


                //RecyclerView Listener
                courseRV.addOnItemTouchListener(new RecyclerItemClickListener(AddDose.this,
                        courseRV,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                timeList.remove(position);
                                courseRV.setLayoutManager(linearLayoutManager);
                                courseRV.setAdapter(timeAdapter);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                // Long item click
                            }
                        }));

            }
            //If time is not selected
            else {
                Toast.makeText(getApplicationContext(), "Select time", Toast.LENGTH_SHORT).show();
            }

        });

        //Date picker Dialog
        datePicker = findViewById(R.id.from);
        //set listener on button click
        datePicker.setOnClickListener(view -> showDatePickerDialog());


        //addDose Button
        addDoseButton = findViewById(R.id.addDoseButton);
        /*
            addDoseButton.setOnClickListener(v -> {
                    EditText instructions_field = (EditText) findViewById(R.id.instruction);
                    EditText units_left_field = (EditText) findViewById(R.id.Units_left);
                    EditText no_of_dose_field = (EditText) findViewById(R.id.Dose);
                    Spinner dose_units_field = (Spinner) findViewById(R.id.dose_units);
                    Spinner day_type_spinner = (Spinner) findViewById(R.id.dayTypeSpinner);

                    String medicine = "Cipla Prograf";
                    String instructions = instructions_field.toString();
                    String dose = dose_units_field.toString();


                });
        */


    }


    //DayTime Spinner event listener functionality
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        ca.antonious.materialdaypicker.MaterialDayPicker specificDay = findViewById(R.id.enabledDaysSelector);
        LinearLayout customDays = findViewById(R.id.customDaysContainer);
        switch (item) {
            case "Specific Days of week":
                //Toast.makeText(getApplicationContext(), "Specific Days of week", Toast.LENGTH_SHORT).show();
                specificDay.clearSelection();
                specificDay.setVisibility(ca.antonious.materialdaypicker.MaterialDayPicker.VISIBLE);
                customDays.setVisibility(View.GONE);
                break;
            case "Everyday":
                //Toast.makeText(getApplicationContext(), "EVERYDAY", Toast.LENGTH_SHORT).show();
                specificDay.selectAllDays();
                specificDay.setVisibility(ca.antonious.materialdaypicker.MaterialDayPicker.VISIBLE);
                customDays.setVisibility(View.GONE);
                break;
            case "Custom days":
                //Toast.makeText(getApplicationContext(), "Custom Day", Toast.LENGTH_SHORT).show();
                specificDay.setVisibility(ca.antonious.materialdaypicker.MaterialDayPicker.GONE);
                customDays.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    //Back Button functionality
    public void backFunction() {
        onBackPressed();
    }

    //Finish Button functionality
    public void finishFunction() {
        Intent send1 = new Intent(AddDose.this, MainActivity.class);
        startActivity(send1);
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
        TextView from = findViewById(R.id.from);
        String text = "Starting from " + date + "/" + (month + 1) + "/" + year;
        from.setText(text);
    }

    //Back Button functionality
    @Override
    public void onBackPressed() {
        if (!timeList.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.pills_time_logo)
                    .setTitle("Pills-Time")
                    .setMessage("Unsaved changes. Are you sure you want to discard changes and go back?")
                    .setPositiveButton("Yes", (dialog, which) -> finish())
                    .setNegativeButton("No", null)
                    .show();
        } else {
            finish();
        }
    }

}
