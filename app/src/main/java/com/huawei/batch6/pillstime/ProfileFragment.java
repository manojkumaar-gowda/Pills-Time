package com.huawei.batch6.pillstime;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Calendar;


public class ProfileFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    //Spinner Options
    private final String[] heights = {"cm", "m", "feet"};
    private final String[] weights = {"kg", "pound"};
    private final String[] gender = {"Male", "Female", "Prefer not to say"};

    //Date Picker
    private TextView pickDate;

    //Spinners
    private Spinner height_spinner;
    private Spinner weight_spinner;
    private Spinner gender_spinner;


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


        //Set heights for spinner
        height_spinner = v.findViewById(R.id.height_units);
        ArrayAdapter heightArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, heights);
        heightArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        height_spinner.setAdapter(heightArrayAdapter);

        //Set weights for spinner
        weight_spinner = v.findViewById(R.id.weight_units);
        ArrayAdapter weightArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, weights);
        weightArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weight_spinner.setAdapter(weightArrayAdapter);

        //Set gender for spinner
        gender_spinner = v.findViewById(R.id.gender);
        ArrayAdapter genderArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, gender);
        genderArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(genderArrayAdapter);


        //datePicker
        //Button click event handler
        pickDate = v.findViewById(R.id.dob);
        pickDate.setOnClickListener(view -> showDatePickerDialog());

        return v;
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