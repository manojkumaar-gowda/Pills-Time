package com.huawei.batch6.pillstime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView exitButton = findViewById(R.id.exit);
        exitButton.setOnClickListener(view -> exitFunction());
        TextView regButton = findViewById(R.id.reg);
        regButton.setOnClickListener(view -> regFunction());
    }
    public void exitFunction(){
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.pills_time_logo)
                .setTitle("Pills-Time")
                .setMessage("Are you sure you want to close this application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       finishAffinity();

                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void regFunction(){
        Intent send = new Intent(LoginActivity.this,AccountRegistration.class);
        startActivity(send);
    }
}