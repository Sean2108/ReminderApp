package com.sean.reminderapp;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class NewReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
    }

    public void onClickCancel(View v) {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClickNewReminder(View v) {
        DBHandler db = new DBHandler(this);

        EditText title = (EditText) findViewById(R.id.newtitle);
        EditText desc = (EditText) findViewById(R.id.newdesc);
        EditText reminderDate = (EditText) findViewById(R.id.newreminderdate);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        Date creationDate = new Date();
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        Reminder reminder = new Reminder(1,title.getText().toString(),desc.getText().toString(),
                dateFormat.format(creationDate),reminderDate.getText().toString());
        db.addReminder(reminder);
        Intent intent = new Intent();
        setResult(2,intent);
        finish();
    }
}
