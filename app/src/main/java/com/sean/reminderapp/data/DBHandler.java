package com.sean.reminderapp.data;

import com.sean.reminderapp.Reminder;

import java.util.List;

/**
 * Created by Sean on 7/4/2017.
 */

public interface DBHandler {
    public void addReminder(Reminder reminder);
    public Reminder getReminder(int id);
    public List<Reminder> getAllReminders();
    public int getRemindersCount();
    public int updateReminder(Reminder reminder);
    public void deleteReminder(Reminder reminder);
}
