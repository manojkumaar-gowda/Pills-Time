package com.huawei.batch6.pillstime;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ProfileFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    //format BMI
    private static final DecimalFormat df = new DecimalFormat("0.00");


    //Max heights and weight accepted
    private final static int maxHeightForCM = 260;
    private final static double maxHeightForM = 2.6;
    private final static double maxWeightForKG = 635;
    private final static double maxWeightForPound = 1399.94;


    //Spinner Options
    private final String[] heights = {"Select Unit", "cm", "m"};
    private final String[] weights = {"Select Unit", "kg", "pound"};
    private final String[] genders = {"Select Gender", "Male", "Female", "Prefer not to say"};

    //colors for BMI
    private String warningColor = "#FCBB42";
    private String dangerColor = "#E99074";
    private String normalColor = "#198754";

    //advice for BMI
    private String warning;
    private String danger;
    private String normal;

    //Logout
    private TextView logoutButton;
    //BMI
    private TextView bmi;
    private TextView bmiSuggestion;

    //update profile
    private Button updateProfile;

    //Date Picker
    private EditText pickDate;

    //Spinners
    private Spinner height_spinner;
    private Spinner weight_spinner;
    private Spinner gender_spinner;

    //Form attributes
    private EditText name;
    private EditText height;
    private EditText weight;
    private Spinner heightUnit;
    private Spinner weightUnit;
    private EditText phoneNumber;
    private EditText dateOfBirth;
    private Spinner gender;

    //default details
    private String nameValue;
    private String heightValue;
    private String weightValue;
    private String heightUnitValue;
    private String weightUnitValue;
    private String phoneNumberValue;
    private String dateOfBirthValue;
    private String genderValue;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        //Logout event handling
        logoutButton = v.findViewById(R.id.log_out);
        logoutButton.setOnClickListener(view -> logoutFunction(v));

        //set bmi advice text
        warning = getString(R.string.warning_bmi_text);
        danger = getString(R.string.danger_bmi_text);
        normal = getString(R.string.normal_bmi_text);

        //set spinners
        setSpinners(v);

        //set default details
        setDefaultDetails(v);

        //set BMI status
        setBMI(v);

        //datePicker
        //Button click event handler
        pickDate = v.findViewById(R.id.dob);
        pickDate.setOnClickListener(view -> showDatePickerDialog());

        //updateProfile
        updateProfile = v.findViewById(R.id.updateButton);
        updateProfile.setOnClickListener(view -> updateProfileFunction(v));


        return v;
    }

    //BMI calculator
    private Double BMICalculator() {
        double w, h;
        //user height converted always to meters
        if (heightUnitValue.equals("cm")) {
            h = Double.valueOf(heightValue) / 100;
        } else {
            h = Double.valueOf(heightValue);
        }
        //user weight converted always to kgs
        if (weightUnitValue.equals("pound")) {
            w = Double.valueOf(weightValue) / 2.205;
        } else {
            w = Double.valueOf(weightValue);
        }
        double BMI = w / (h * h);
        return BMI;
    }

    //setting BMI text and showing suggestions
    private void setBMI(View v) {
        bmi = v.findViewById(R.id.bmi);
        bmiSuggestion = v.findViewById(R.id.advice);
        double BMI = BMICalculator();
        //underweight
        if (BMI < 18.5) {
            bmi.setText(String.valueOf(df.format(BMI)));
            bmi.setTextColor(Color.parseColor(warningColor));
            bmiSuggestion.setText(warning);
        }//obese
        else if (BMI > 24.9) {
            bmi.setText(String.valueOf(df.format(BMI)));
            bmi.setTextColor(Color.parseColor(dangerColor));
            bmiSuggestion.setText(danger);
        }//normal bmi
        else {
            bmi.setText(String.valueOf(df.format(BMI)));
            bmi.setTextColor(Color.parseColor(normalColor));
            bmiSuggestion.setText(normal);
        }

    }

    private void updateProfileFunction(View v) {
        //link the inputs
        name = v.findViewById(R.id.name);
        height = v.findViewById(R.id.height);
        weight = v.findViewById(R.id.weight);
        heightUnit = v.findViewById(R.id.height_units);
        weightUnit = v.findViewById(R.id.weight_units);
        phoneNumber = v.findViewById(R.id.phone_number);
        dateOfBirth = v.findViewById(R.id.dob);
        gender = v.findViewById(R.id.gender);


        //get input values
        String nameValue = name.getText().toString();
        String heightValue = height.getText().toString().trim().toLowerCase(Locale.ROOT);
        String weightValue = weight.getText().toString();
        String heightUnitValue = heightUnit.getSelectedItem().toString();
        String weightUnitValue = weightUnit.getSelectedItem().toString();
        String phoneNumberValue = phoneNumber.getText().toString();
        String dateOfBirthValue = dateOfBirth.getText().toString();
        String genderValue = gender.getSelectedItem().toString();


        //assuming that no validation errors exist
        Boolean noVaidationErrors = true;

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
            Toast.makeText(getContext(), "Invalid Height Unit", Toast.LENGTH_SHORT).show();
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
                    if (Integer.valueOf(heightValue) > maxHeightForCM) {
                        height.setError("Range out of limit");
                        noVaidationErrors = false;
                    }
                } catch (Exception e) {
                    height.setError("Height in centimeters cannot be in decimal");
                    noVaidationErrors = false;
                }
            }//for m height should be < maxHeightForM
            else if (heightUnitValue.equals("m")) {
                if (Double.valueOf(heightValue) > maxHeightForM) {
                    height.setError("Range out of limit");
                    noVaidationErrors = false;
                }
            }
        }


        //check weight validity
        //invalid unit
        if (weightUnitValue.equals("Select Unit")) {
            Toast.makeText(getContext(), "Invalid Weight Unit", Toast.LENGTH_SHORT).show();
            noVaidationErrors = false;
        }
        //empty weight field
        if (weightValue.equals("")) {
            weight.setError("Please enter a valid weight");
            noVaidationErrors = false;
        } else {
            //for kg weight should be < maxWeightForKG
            if (weightUnitValue.equals("kg")) {
                if (Double.valueOf(weightValue) > maxWeightForKG) {
                    weight.setError("Range out of limit");
                    noVaidationErrors = false;
                }
            }//for pounds weight should be < maxWeightForPound
            else if (weightUnitValue.equals("pound")){
                if (Double.valueOf(weightValue) > maxWeightForPound) {
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
            Toast.makeText(getContext(), "Please select you gender", Toast.LENGTH_SHORT).show();
            noVaidationErrors = false;
        }

        if (noVaidationErrors) {
            Toast.makeText(getContext(), "Details updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Please fill in proper details", Toast.LENGTH_SHORT).show();
        }

    }

    //set default user details
    private void setDefaultDetails(View v) {
        //get default values
        nameValue = "MOJI";
        heightValue = "1.72";
        weightValue = "40";
        heightUnitValue = "m";
        weightUnitValue = "kg";
        phoneNumberValue = "6383664520";
        dateOfBirthValue = "8/12/2022";
        genderValue = "Male";
        //link to front end
        name = v.findViewById(R.id.name);
        height = v.findViewById(R.id.height);
        weight = v.findViewById(R.id.weight);
        heightUnit = v.findViewById(R.id.height_units);
        weightUnit = v.findViewById(R.id.weight_units);
        phoneNumber = v.findViewById(R.id.phone_number);
        dateOfBirth = v.findViewById(R.id.dob);
        gender = v.findViewById(R.id.gender);

        //non editable phone number and dob
        phoneNumber.setFocusable(false);
        phoneNumber.setCursorVisible(false);
        dateOfBirth.setFocusable(false);
        dateOfBirth.setCursorVisible(false);
        dateOfBirth.setKeyListener(null);


        //set values
        name.setText(nameValue);
        height.setText(heightValue);
        weight.setText(weightValue);
        heightUnit.setSelection(findIndex(heightUnitValue, heights));
        weightUnit.setSelection(findIndex(weightUnitValue, weights));
        phoneNumber.setText(phoneNumberValue);
        dateOfBirth.setText(dateOfBirthValue);
        gender.setSelection(findIndex(genderValue, genders));
    }

    private int findIndex(String key, String[] spinnerArray) {
        for (int i = 0; i < spinnerArray.length; i++) {
            if (spinnerArray[i].equals(key)) {
                return i;
            }
        }
        return 0;

    }


    //set spinner functionality
    private void setSpinners(View v) {
        //Set heights for spinner
        height_spinner = v.findViewById(R.id.height_units);
        List<String> heightList = new ArrayList<>(Arrays.asList(heights));
        ArrayAdapter<String> heightSpinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, heightList) {
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
        weight_spinner = v.findViewById(R.id.weight_units);
        List<String> weightList = new ArrayList<>(Arrays.asList(weights));
        ArrayAdapter<String> weightSpinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, weightList) {
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
        gender_spinner = v.findViewById(R.id.gender);
        List<String> genderList = new ArrayList<>(Arrays.asList(genders));
        ArrayAdapter<String> genderSpinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, genderList) {
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

    //Logout functionality
    public void logoutFunction(View v) {
        Toast.makeText(getContext(), "Log out", Toast.LENGTH_SHORT).show();
    }

    //Date Picker functionality
    private void showDatePickerDialog() {
        //create a new DatePickerDialog object and set the default selected date to present dat
        DatePickerDialog datePicker = new DatePickerDialog(
                getActivity(),
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