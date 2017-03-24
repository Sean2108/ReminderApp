package com.sean.reminderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
    }

    public void onClickCancel(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickNewReminder(View v) {
        DBHandler db = new DBHandler(this);
        Reminder reminder = new Reminder();
        EditText title = (EditText) findViewById(R.id.newtitle);
        EditText desc = (EditText) findViewById(R.id.newdesc);
        EditText reminderDate = (EditText) findViewById(R.id.newreminderdate);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        Date creationDate = new Date();

        reminder.setTitle(title.getText().toString());
        reminder.setDescription(desc.getText().toString());
        reminder.setCreationDate(dateFormat.format(creationDate));
        reminder.setReminderDate(reminderDate.getText().toString());
        db.addReminder(reminder);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
