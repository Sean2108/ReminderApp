package com.sean.reminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);
        List<Reminder> reminders = db.getAllReminders();

        //for (Reminder r: reminders) db.deleteReminder(r);

        RemindersAdapter adapter = new RemindersAdapter(this, reminders);
        ListView listView = (ListView) findViewById(R.id.listReminders);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    public void onClickBtn(View v) {
        Intent intent = new Intent(this, NewReminderActivity.class);
        startActivityForResult(intent,1);
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reminder_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        DBHandler db = new DBHandler(this);
        List<Reminder> reminders = db.getAllReminders();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Reminder toBeChanged = reminders.get((int)info.id);
        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(this,EditReminderActivity.class);
                intent.putExtra("TO_BE_CHANGED_ID", toBeChanged.getId());
                startActivityForResult(intent,1);
                return true;
            case R.id.delete:
                db.deleteReminder(toBeChanged);
                finish();
                startActivity(getIntent());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            finish();
            startActivity(getIntent());
        }
    }
}