package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import java.time.LocalDate;



public class Statistics extends AppCompatActivity {
    private final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Noc", "Dec"};
    LocalDate currentDate = LocalDate.now();
    private int selectedDate = currentDate.getDayOfMonth();
    private int selectedMonth = currentDate.getMonthValue();
    private int selectedYear = currentDate.getYear();
    private String selectedMonthName = months[currentDate.getMonthValue()-1];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        TextView detailedSummary = findViewById(R.id.detailed_summmary);
        detailedSummary.setOnClickListener(view -> detailedSummaryFunction());
        CalendarView calender = findViewById(R.id.selectDate);
        calender.setOnDateChangeListener((view, year, month, dateOfMonth) -> {
            selectedDate = dateOfMonth;
            selectedMonth = month + 1;
            selectedMonthName = months[month];
            selectedYear = year;
            //Toast.makeText(getApplicationContext(), String.valueOf(selectedYear), Toast.LENGTH_SHORT).show();
        });

    }

    public void detailedSummaryFunction() {
        Intent send = new Intent(Statistics.this, DetailedStatistics.class);
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