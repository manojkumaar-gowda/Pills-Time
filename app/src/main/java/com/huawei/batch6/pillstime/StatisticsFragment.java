package com.huawei.batch6.pillstime;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;


public class StatisticsFragment extends Fragment {

    //pull to refresh
    private SwipeRefreshLayout pullToRefresh;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    //Percentage
    private TextView percentageText;
    //progress bar
    private ProgressBar progressBar;

    //Month array
    private final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Noc", "Dec"};


    //Selected Date Attributes
    private LocalDate currentDate;
    private int selectedDate;
    private int selectedMonth;
    private int selectedYear;
    private String selectedMonthName;

    //detailed statistics
    TextView detailedSummary;

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

        //pull to refresh functionality
        pullToRefresh = v.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                pullToRefresh.setRefreshing(false);
            }

            private void refresh() {
                Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
            }
        });

        //set date to current date;
        currentDate = LocalDate.now();
        selectedDate = currentDate.getDayOfMonth();
        selectedMonth = currentDate.getMonthValue();
        selectedYear = currentDate.getYear();
        selectedMonthName = months[currentDate.getMonthValue() - 1];

        //set progress of that particular date
        changePercentage(v);

        //detailed statistics event handler
        detailedSummary = v.findViewById(R.id.detailed_summmary);
        detailedSummary.setOnClickListener(view -> detailedSummaryFunction());


        //On date change listener for calenderView
        CalendarView calender = v.findViewById(R.id.selectDate);
        calender.setOnDateChangeListener((view, year, month, dateOfMonth) -> {
            selectedDate = dateOfMonth;
            selectedMonth = month + 1;
            selectedMonthName = months[month];
            selectedYear = year;
            changePercentage(v);
        });
        return v;
    }

    //change percentage and progress according to the date
    private void changePercentage(View v) {
        percentageText = v.findViewById(R.id.progress_percentage);
        progressBar = v.findViewById(R.id.progressBar);
        percentageText.setText(String.valueOf(selectedDate) + "%");
        progressBar.setProgress(selectedDate);
    }

    //detailed statistics button functionality
    public void detailedSummaryFunction() {
        Intent send = new Intent(getActivity(), DetailedStatistics.class);
        Bundle bundle = new Bundle();
        bundle.putString("date", String.valueOf(selectedDate));
        bundle.putString("month", String.valueOf(selectedMonth));
        bundle.putString("monthName", String.valueOf(selectedMonthName));
        bundle.putString("year", String.valueOf(selectedYear));
        bundle.putString("progress", String.valueOf(selectedDate));
        send.putExtras(bundle);
        startActivity(send);

    }

}