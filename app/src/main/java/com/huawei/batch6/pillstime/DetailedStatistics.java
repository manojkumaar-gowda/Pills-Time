package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedStatistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_statistics);
        Bundle bundle = getIntent().getExtras();
        TextView date = findViewById(R.id.date);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView progress_percentage = findViewById(R.id.progress_percentage);
        String fullDate = bundle.getString("monthName")+",\n"+bundle.getString("date")+" "+bundle.getString("year");
        String progress = bundle.getString("progress", "0");
        progressBar.setProgress(Integer.parseInt(progress));
        date.setText(fullDate);
        String progressWithPercentage = progress+"%";
        progress_percentage.setText(progressWithPercentage);

        TextView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> backFunction());




        RecyclerView refillMedsRV = findViewById(R.id.medsCardView);
        ArrayList<UpcomingEventsModel> upcomingEventsModels;
        // here we have created new array list and added data to it.
        upcomingEventsModels = new ArrayList<>();
        upcomingEventsModels.add(new UpcomingEventsModel("8.00 AM","Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg",getRandomColor(DetailedStatistics.this)));
        upcomingEventsModels.add(new UpcomingEventsModel("8.10 AM","Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg",getRandomColor(DetailedStatistics.this)));
        upcomingEventsModels.add(new UpcomingEventsModel("3.00 PM","Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg",getRandomColor(DetailedStatistics.this)));
        upcomingEventsModels.add(new UpcomingEventsModel("10.00 PM","Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg",getRandomColor(DetailedStatistics.this)));


        // we are initializing our adapter class and passing our arraylist to it.
        UpcomingEventsAdapter upcomingEventsAdapter = new UpcomingEventsAdapter(null, upcomingEventsModels);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(null, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        refillMedsRV.setLayoutManager(linearLayoutManager);
        refillMedsRV.setAdapter(upcomingEventsAdapter);


    }
    public void backFunction(){
        finish();
    }

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
