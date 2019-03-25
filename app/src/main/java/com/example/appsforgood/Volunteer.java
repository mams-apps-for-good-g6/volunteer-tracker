package com.example.appsforgood;

import java.util.ArrayList;

public class Volunteer extends User
{
    // DATA

    private double totalHours;
    private ArrayList<LogEntry> logEntries;

    // CONSTRUCTOR

    public Volunteer(String first, String last, String email)
    {
        super(first, last, email);
        totalHours = 0;
    }

    // METHODS

    /**
     * Adds a log entry to the user's list of log entries.
     * @param charityName name of the organization that the volunteer served at
     * @param hours hours served
     * @param date date served
     * @param contactName contact person's full name
     * @param contactEmail contact person's email address
     */
    public void logHours(String charityName, double hours, String date, String contactName, String contactEmail)
    {
        logEntries.add(new LogEntry(charityName, hours, date, contactName, contactEmail));
        totalHours+=hours;
    }

    public double getTotalHours() {return totalHours;}

    public String getLog()
    {
        String log = "Charity \t Hours \t Date";
        for (LogEntry entry: logEntries)
        {
            log = "\n" + entry.getCharityName() + "\t" + entry.getHours() + "\t" + entry.getDate();
        }
        return log;
    }

    public double getSpecificCharityHours(String charityName)
    {
        for (LogEntry entry: logEntries)
        {
            int hours = 0;
            if (entry.getCharityName().equals(charityName))
            {
                hours+=entry.getHours();
            }
        }
        return hours;
    }

}
