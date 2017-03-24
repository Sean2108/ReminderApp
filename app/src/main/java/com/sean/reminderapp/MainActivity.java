package com.sean.reminderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler db = new DBHandler(this);
        db.addReminder(new Reminder(1,"Dockers", "475 Brannan St #330, San Francisco, CA 94107, United States", "21/1", "22/2"));
        List<Reminder> reminders = db.getAllReminders();
        RemindersAdapter adapter = new RemindersAdapter(this, reminders);
        ListView listView = (ListView) findViewById(R.id.listReminders);
        listView.setAdapter(adapter);
    }

    public void onClickBtn(View v) {
        Intent intent = new Intent(this, NewReminderActivity.class);
        startActivity(intent);
    }
}