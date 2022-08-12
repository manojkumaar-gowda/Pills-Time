package com.huawei.batch6.pillstime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    //Exit Button
    private TextView exitButton;

    // Temporary Registraion page button
    private TextView regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Exit button event landling
        exitButton = findViewById(R.id.exit);
        exitButton.setOnClickListener(view -> exitFunction());

        //Registration button event landling
        regButton = findViewById(R.id.reg);
        regButton.setOnClickListener(view -> regFunction());
    }

    //Exit functionality
    public void exitFunction() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.pills_time_logo)
                .setTitle("Pills-Time")
                .setMessage("Are you sure you want to close this application?")
                .setPositiveButton("Yes", (dialog, which) -> finishAffinity())
                .setNegativeButton("No", null)
                .show();
    }

    //Registraion functionality
    public void regFunction() {
        Intent send = new Intent(LoginActivity.this, AccountRegistration.class);
        startActivity(send);
    }
}