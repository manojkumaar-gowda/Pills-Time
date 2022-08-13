package com.huawei.batch6.pillstime;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AccountRegistration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    //pull to refresh
    private SwipeRefreshLayout pullToRefresh;

    //Spinner Options
    private final String[] heights = {"Select Unit", "cm", "m"};
    private final String[] weights = {"Select Unit", "kg", "pound"};
    private final String[] genders = {"Select Gender", "Male", "Female", "Prefer not to say"};

    //Date Pickers
    private EditText pickDate;
    //Logout
    private TextView logoutButton;
    //Temporary way to dashboard
    private TextView accButton;
    //create profile
    private Button createProfile;
    //Terms and services
    private TextView termsAndServices;

    //Max heights and weight accepted
    private final static int maxHeightForCM = 260;
    private final static double maxHeightForM = 2.6;
    private final static double maxWeightForKG = 635;
    private final static double maxWeightForPound = 1399.94;

    //user details
    private String UserphNumber;
    private String Userlocation;

    //Form attributes
    private EditText name;
    private EditText height;
    private EditText weight;
    private Spinner heightUnit;
    private Spinner weightUnit;
    private EditText phoneNumber;
    private EditText dateOfBirth;
    private Spinner gender;
    private EditText location;
    private CheckBox areTermsAccepted;

    //Spinners
    private Spinner height_spinner;
    private Spinner weight_spinner;
    private Spinner gender_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_registration);

        //pull to refresh functionality
        pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                pullToRefresh.setRefreshing(false);
            }

            private void refresh() {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });



        //setUserDetails from huawei login
        UserphNumber = "6383664520";
        Userlocation = "38°56’23”N, 71°0’36”W";

        //Logout event handling
        logoutButton = findViewById(R.id.log_out);
        logoutButton.setOnClickListener(view -> logoutFunction());

        //Temporary way to dashboard
        accButton = findViewById(R.id.dash);
        accButton.setOnClickListener(view -> accFunction());

        //set spinners
        setSpinners();

        //datePicker
        //Button click event handler
        pickDate = findViewById(R.id.dob);
        pickDate.setOnClickListener(view -> showDatePickerDialog());
        pickDate.setFocusable(false);
        pickDate.setCursorVisible(false);
        pickDate.setKeyListener(null);

        //termsAndServices
        termsAndServices = findViewById(R.id.agree_terms_text);
        termsAndServices.setOnClickListener(v -> openTermsFunction());


        //createProfile
        createProfile = findViewById(R.id.createProfile);
        createProfile.setOnClickListener(v -> createProfileFunction());

        //set user location
        location = findViewById(R.id.location);
        location.setText(Userlocation);
        location.setFocusable(false);
        location.setCursorVisible(false);
        location.setKeyListener(null);

        //phone number not focusable
        phoneNumber = findViewById(R.id.phone_number);
        phoneNumber.setText(UserphNumber);
        phoneNumber.setFocusable(false);
        phoneNumber.setCursorVisible(false);


    }

    //open terms of services function
    private void openTermsFunction() {
        Toast.makeText(getApplicationContext(), "Terms of services", Toast.LENGTH_SHORT).show();
    }

    //set spinner functionality
    private void setSpinners() {
        //Set heights for spinner
        height_spinner = findViewById(R.id.height_units);
        List<String> heightList = new ArrayList<>(Arrays.asList(heights));
        ArrayAdapter<String> heightSpinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, heightList) {
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
        heightSpinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        height_spinner.setAdapter(heightSpinnerArrayAdapter);

        //Set weights for spinner
        weight_spinner = findViewById(R.id.weight_units);
        List<String> weightList = new ArrayList<>(Arrays.asList(weights));
        ArrayAdapter<String> weightSpinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, weightList) {
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
        weightSpinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        weight_spinner.setAdapter(weightSpinnerArrayAdapter);

        //Set gender for spinner
        gender_spinner = findViewById(R.id.gender);
        List<String> genderList = new ArrayList<>(Arrays.asList(genders));
        ArrayAdapter<String> genderSpinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, genderList) {
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
        genderSpinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        gender_spinner.setAdapter(genderSpinnerArrayAdapter);
    }

    //create profile functionality
    private void createProfileFunction() {
        //link the inputs
        name = findViewById(R.id.name);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        heightUnit = findViewById(R.id.height_units);
        weightUnit = findViewById(R.id.weight_units);
        phoneNumber = findViewById(R.id.phone_number);
        dateOfBirth = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        location = findViewById(R.id.location);
        areTermsAccepted = findViewById(R.id.checkTermsAndServices);

        //get input values
        String nameValue = name.getText().toString();
        String heightValue = height.getText().toString().trim().toLowerCase(Locale.ROOT);
        String weightValue = weight.getText().toString();
        String heightUnitValue = heightUnit.getSelectedItem().toString();
        String weightUnitValue = weightUnit.getSelectedItem().toString();
        String phoneNumberValue = phoneNumber.getText().toString();
        String dateOfBirthValue = dateOfBirth.getText().toString();
        String genderValue = gender.getSelectedItem().toString();
        String locationValue = location.getText().toString();
        boolean areTermsAcceptedStatus = areTermsAccepted.isChecked();


        //assuming that no validation errors exist
        boolean noVaidationErrors = true;

        //check for validation errors
        //check if name field is empty
        if (nameValue.equals("")) {
            name.setError("Please enter a valid name");
            noVaidationErrors = false;
        }
        //name length atleast 3 letters
        if (nameValue.length() < 3) {
            name.setError("Name should be atleast 3 letters in length");
            noVaidationErrors = false;
        }


        //check height validity
        //invalid unit
        if (heightUnitValue.equals("Select Unit")) {
            Toast.makeText(getApplicationContext(), "Invalid Height Unit", Toast.LENGTH_SHORT).show();
            noVaidationErrors = false;
        }
        //empty height field
        if (heightValue.equals("")) {
            height.setError("Please enter a valid height");
            noVaidationErrors = false;
        } else {
            //for cm height should be < maxHeightForCM
            if (heightUnitValue.equals("cm")) {
                try {
                    if (Integer.parseInt(heightValue) > maxHeightForCM) {
                        height.setError("Range out of limit");
                        noVaidationErrors = false;
                    }
                } catch (Exception e) {
                    height.setError("Height in centimeters cannot be in decimal");
                    noVaidationErrors = false;
                }
            }//for m height should be < maxHeightForM
            else if (heightUnitValue.equals("m")) {
                if (Double.parseDouble(heightValue) > maxHeightForM) {
                    height.setError("Range out of limit");
                    noVaidationErrors = false;
                }
            }
        }


        //check weight validity
        //invalid unit
        if (weightUnitValue.equals("Select Unit")) {
            Toast.makeText(getApplicationContext(), "Invalid Weight Unit", Toast.LENGTH_SHORT).show();
            noVaidationErrors = false;
        }
        //empty weight field
        if (weightValue.equals("")) {
            weight.setError("Please enter a valid weight");
            noVaidationErrors = false;
        } else {
            //for kg weight should be < maxWeightForKG
            if (weightUnitValue.equals("kg")) {
                if (Double.parseDouble(weightValue) > maxWeightForKG) {
                    weight.setError("Range out of limit");
                    noVaidationErrors = false;
                }
            }//for pounds weight should be < maxWeightForPound
            else if (weightUnitValue.equals("pound")) {
                if (Double.parseDouble(weightValue) > maxWeightForPound) {
                    weight.setError("Range out of limit");
                    noVaidationErrors = false;
                }
            }
        }


        //phone number validation
        if (phoneNumberValue.length() != 10) {
            phoneNumber.setError("Invalid Phone Number");
            noVaidationErrors = false;
        }

        //Date of Birth validation
        if (dateOfBirthValue.equals("")) {
            dateOfBirth.setError("Invalid Date Of Birth");
            noVaidationErrors = false;
        } else {
            dateOfBirth.setError(null);
        }

        //invalid gender
        if (genderValue.equals("Select Gender")) {
            Toast.makeText(getApplicationContext(), "Please select you gender", Toast.LENGTH_SHORT).show();
            noVaidationErrors = false;
        }

        //termsAndConditions validation
        if (!areTermsAcceptedStatus) {
            areTermsAccepted.setError("Its mandatory that you accept the terms and conditions");
            Toast.makeText(getApplicationContext(), "Its mandatory that you accept the terms and conditions", Toast.LENGTH_SHORT).show();
            noVaidationErrors = false;
        } else {
            areTermsAccepted.setError(null);
        }


        if (noVaidationErrors) {
            Toast.makeText(getApplicationContext(), "Details Registered", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please fill in proper details", Toast.LENGTH_SHORT).show();
        }

    }

    //Logout functionality
    public void logoutFunction() {
        Toast.makeText(getApplicationContext(), "Log out", Toast.LENGTH_SHORT).show();
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