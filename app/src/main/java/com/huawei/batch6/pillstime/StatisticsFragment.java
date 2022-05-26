package com.huawei.batch6.pillstime;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.time.LocalDate;


public class StatisticsFragment extends Fragment {
    private final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Noc", "Dec"};
    LocalDate currentDate = LocalDate.now();
    private int selectedDate = currentDate.getDayOfMonth();
    private int selectedMonth = currentDate.getMonthValue();
    private int selectedYear = currentDate.getYear();
    private String selectedMonthName = months[currentDate.getMonthValue()-1];

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatisticsFragment() {
        // Required empty public constructor
    }



    public static StatisticsFragment newInstance(String param1, String param2) {
        StatisticsFragment fragment = new StatisticsFragment();
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

        View v = inflater.inflate(R.layout.fragment_statistics, container, false);
        TextView detailedSummary = v.findViewById(R.id.detailed_summmary);
        detailedSummary.setOnClickListener(view -> detailedSummaryFunction());
        CalendarView calender = v.findViewById(R.id.selectDate);
        calender.setOnDateChangeListener((view, year, month, dateOfMonth) -> {
            selectedDate = dateOfMonth;
            selectedMonth = month + 1;
            selectedMonthName = months[month];
            selectedYear = year;
            //Toast.makeText(getApplicationContext(), String.valueOf(selectedYear), Toast.LENGTH_SHORT).show();
        });
        return v;
    }

    public void detailedSummaryFunction() {
        Intent send = new Intent(getActivity(), DetailedStatistics.class);
        Bundle bundle = new Bundle();
        bundle.putString("date", String.valueOf(selectedDate));
        bundle.putString("month", String.valueOf(selectedMonth));
        bundle.putString("monthName", String.valueOf(selectedMonthName));
        bundle.putString("year", String.valueOf(selectedYear));
        bundle.putString("progress", "80");
        send.putExtras(bundle);
        startActivity(send);

    }

}