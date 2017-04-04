package com.sean.reminderapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Sean on 4/4/2017.
 */

public class Dialog {
    private Reminder toBeChanged;
    private DBHandler db;
    private Context context;
    private Activity activity;

    public Dialog(Reminder toBeChanged, DBHandler db, Context context, Activity activity) {
        this.toBeChanged = toBeChanged;
        this.db = db;
        this.context = context;
        this.activity = activity;
    }

    public void buildDialog(String title, String message, final String toastMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                db.deleteReminder(toBeChanged);
                activity.finish();
                activity.startActivity(activity.getIntent());
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
