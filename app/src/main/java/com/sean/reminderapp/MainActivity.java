package com.sean.reminderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHandler db = new DBHandler(this);
    List<Reminder> reminders = db.getAllReminders();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RemindersAdapter adapter = new RemindersAdapter(this, reminders);
        ListView listView = (ListView) findViewById(R.id.listReminders);
        listView.setAdapter(adapter);
    }

    public void onClickBtn(View v) {
        Intent intent = new Intent(this, NewReminderActivity.class);
        startActivity(intent);
    }
}