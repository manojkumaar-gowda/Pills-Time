package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailedStatistics extends AppCompatActivity {

    //pull to refresh
    private SwipeRefreshLayout pullToRefresh;

    // Back
    private TextView backButton;
    private TextView date;
    private ProgressBar progressBar;
    private TextView progress_percentage;


    //progress
    private int progress;
    private String selectedDate;
    private String selectedMonth;
    private String selectedYear;
    private String fullDate;

    //Month array
    private final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Noc", "Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_statistics);

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


        //back button event handling
        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> backFunction());

        //link date, progreeBar,progress_percentage to the frontend
        date = findViewById(R.id.date);
        progressBar = findViewById(R.id.progressBar);
        progress_percentage = findViewById(R.id.progress_percentage);


        //Extract date content from the previous activity
        Bundle bundle = getIntent().getExtras();
        selectedDate = bundle.getString("date");
        selectedMonth = bundle.getString("monthName");
        selectedYear = bundle.getString("year");


        fullDate = selectedMonth + ",\n" + selectedDate + " " + selectedYear;
        progress = Integer.parseInt(bundle.getString("progress", "0"));

        //set values for the progress
        progressBar.setProgress(progress);
        date.setText(fullDate);
        String progressWithPercentage = progress + "%";
        progress_percentage.setText(progressWithPercentage);

        //set detailed statistics recyclable view
        setDetailedStatistics(fullDate);


    }

    //change percentage and progress according to the date
    private void changePercentage(View v) {
        progress_percentage = v.findViewById(R.id.progress_percentage);
        progressBar = v.findViewById(R.id.progressBar);
        progress_percentage.setText(String.valueOf(selectedDate) + "%");
        progressBar.setProgress(Integer.parseInt(selectedDate));
    }


    //create detailed statistics recyclable view
    private void setDetailedStatistics(String fullDate) {
        RecyclerView detailedStatisticsRecyclable_view = findViewById(R.id.detailed_stat_rv);
        ArrayList<DetailedStatModel> detailedStatModelsArrayList;
        // here we have created new array list and added data to it.
        detailedStatModelsArrayList = new ArrayList<>();
        detailedStatModelsArrayList.add(new DetailedStatModel("8.00 AM", fullDate, "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", getRandomColor(DetailedStatistics.this)));
        detailedStatModelsArrayList.add(new DetailedStatModel("8.10 AM", fullDate, "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", getRandomColor(DetailedStatistics.this)));
        detailedStatModelsArrayList.add(new DetailedStatModel("3.00 PM", fullDate, "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", getRandomColor(DetailedStatistics.this)));
        detailedStatModelsArrayList.add(new DetailedStatModel("10.00 PM", fullDate, "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", getRandomColor(DetailedStatistics.this)));

        // we are initializing our adapter class and passing our arraylist to it.
        DetailedStatAdapter detailedStatAdapter = new DetailedStatAdapter(null, detailedStatModelsArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(null, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        detailedStatisticsRecyclable_view.setLayoutManager(linearLayoutManager);
        detailedStatisticsRecyclable_view.setAdapter(detailedStatAdapter);

    }

    //back button functionality
    public void backFunction() {
        finish();
    }

    //get random colors
    private static int getRandomColor(DetailedStatistics context) {
        int[] colors;
        if (Math.random() >= 0.6) {
            colors = context.getResources().getIntArray(R.array.note_accent_colors);
        } else {
            colors = context.getResources().getIntArray(R.array.note_neutral_colors);
        }
        return colors[((int) (Math.random() * colors.length))];
    }


}
