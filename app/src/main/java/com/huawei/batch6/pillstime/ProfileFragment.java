package com.huawei.batch6.pillstime;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    String[] heights = { "cm", "m", "feet"};

    String[] weights = { "kg", "pound"};

    String[] gender = { "Male", "Female", "Prefer not to say"};


    TextView pickDate;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
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



//heights
        Spinner height_spino = v.findViewById(R.id.height_units);
        ArrayAdapter ad
                = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, heights);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        height_spino.setAdapter(ad);

//weight
        Spinner weight_spino = v.findViewById(R.id.weight_units);
        ArrayAdapter ad1
                = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, weights);
        ad1.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        weight_spino.setAdapter(ad1);

//gender
        Spinner gender_spino = v.findViewById(R.id.gender);
        ArrayAdapter ad2
                = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, gender);
        ad2.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        gender_spino.setAdapter(ad2);


//datePicker
        pickDate = v.findViewById(R.id.dob);
        //set listener on button click
        pickDate.setOnClickListener(view -> showDatePickerDialog());

        return v;
    }

    private void showDatePickerDialog(){
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

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int date){
        //set the date
        String text = date+"/"+(month+1)+"/"+year;
        pickDate.setText(text);
    }


}