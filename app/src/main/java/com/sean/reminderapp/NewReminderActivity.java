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

        EditText title = (EditText) findViewById(R.id.newtitle);
        EditText desc = (EditText) findViewById(R.id.newdesc);
        EditText reminderDate = (EditText) findViewById(R.id.newreminderdate);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        Date creationDate = new Date();
        Reminder reminder = new Reminder(1,title.getText().toString(),desc.getText().toString(),
                dateFormat.format(creationDate),reminderDate.getText().toString());
        /*
        reminder.setTitle(title.getText().toString());
        reminder.setDescription(desc.getText().toString());
        reminder.setCreationDate(dateFormat.format(creationDate));
        reminder.setReminderDate(reminderDate.getText().toString());*/
        //if(reminder.getTitle()!=null) {
            db.addReminder(reminder);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        //}
    }
}
