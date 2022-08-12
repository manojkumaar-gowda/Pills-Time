package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedStatistics extends AppCompatActivity {

    // Back
    private TextView backButton;
    private TextView date;
    private ProgressBar progressBar;
    private TextView progress_percentage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_statistics);

        //back button event handling
        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> backFunction());

        //link date, progreeBar,progress_percentage to the frontend
        date = findViewById(R.id.date);
        progressBar = findViewById(R.id.progressBar);
        progress_percentage = findViewById(R.id.progress_percentage);


        //Extract date content from the previous activity
        Bundle bundle = getIntent().getExtras();
        String fullDate = bundle.getString("monthName") + ",\n" + bundle.getString("date") + " " + bundle.getString("year");
        String progress = bundle.getString("progress", "0");

        //set values for the progress
        progressBar.setProgress(Integer.parseInt(progress));
        date.setText(fullDate);
        String progressWithPercentage = progress + "%";
        progress_percentage.setText(progressWithPercentage);

        //set detailed statistics recyclable view
        setDetailedStatistics(fullDate);


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
