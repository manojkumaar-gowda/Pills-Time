package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    }
    public void backFunction(){
        finish();
    }
}
