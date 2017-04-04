package com.sean.reminderapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHandler db;
    RemindersAdapter adapter;
    List<Reminder> reminders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHandler(this);
        reminders = db.getAllReminders();

        //for (Reminder r: reminders) db.deleteReminder(r);

        adapter = new RemindersAdapter(this, reminders);
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
        db = new DBHandler(this);
        reminders = db.getAllReminders();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Reminder toBeChanged = reminders.get((int)info.id);
        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(this,EditReminderActivity.class);
                intent.putExtra("TO_BE_CHANGED_ID", toBeChanged.getId());
                startActivityForResult(intent,1);
                return true;
            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to delete the reminder?")
                        .setTitle("Delete Reminder");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.deleteReminder(toBeChanged);
                        finish();
                        startActivity(getIntent());
                        Toast.makeText(getApplicationContext(), "Reminder deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!=1) {
            //reminders.clear();
            //reminders.addAll(db.getAllReminders());
            //adapter.notifyDataSetChanged();
            finish();
            startActivity(getIntent());
        }
        if(resultCode==2){
            Toast.makeText(getApplicationContext(), "Reminder edited!", Toast.LENGTH_SHORT).show();
        }
        else if(resultCode==3) {
            Toast.makeText(getApplicationContext(), "Reminder created!", Toast.LENGTH_SHORT).show();
        }
    }
}