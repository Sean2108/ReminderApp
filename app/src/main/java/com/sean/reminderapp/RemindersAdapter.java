package com.sean.reminderapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sean on 25/3/17.
 */

public class RemindersAdapter extends ArrayAdapter<Reminder> {
    public RemindersAdapter(Context context, List<Reminder> reminders) {
        super(context, 0, reminders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Reminder reminder = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_reminder, parent, false);
        }
        // Lookup view for data population
        TextView reminderTitle = (TextView) convertView.findViewById(R.id.reminderTitle);
        TextView reminderDesc = (TextView) convertView.findViewById(R.id.reminderDesc);
        TextView creationDate = (TextView) convertView.findViewById(R.id.creationDate);
        TextView reminderDate =  (TextView) convertView.findViewById(R.id.reminderDate);
        // Populate the data into the template view using the data object
        reminderTitle.setText(reminder.getTitle());
        reminderDesc.setText(reminder.getDescription());
        creationDate.setText(reminder.getCreationDate());
        reminderDate.setText(reminder.getReminderDate());
        // Return the completed view to render on screen
        return convertView;
    }
}
