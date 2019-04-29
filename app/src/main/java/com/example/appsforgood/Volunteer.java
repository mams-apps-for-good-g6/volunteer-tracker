package com.example.appsforgood;

import java.util.ArrayList;

public class Volunteer extends User
{
    // DATA

    private double totalHours;
    private ArrayList<LogEntry> logEntries;
    private int index;
    private String orgPath;

    // CONSTRUCTOR

    public Volunteer(String first, String last, String email, String path)
    {
        super(first, last, email);
        totalHours = 0;
        index = 0;
        logEntries = new ArrayList<>();
        orgPath="";
    }

    public Volunteer() {
        super();
        totalHours=0;
        index = 0;
        logEntries = new ArrayList<>();
        orgPath="";
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

    public void setIndex(int i){index = i;}

    public int getIndex(){return index;}

    public void setOrgPath(String path){orgPath=path;}

    public String getOrgPath(){return orgPath;}

    public double getTotalHours() {return totalHours;}

    /**
     * Returns a string object that displays all log entries. Each entry is displayed on its own line in the following form: charity Name, hours served, date served.
     * @return the log of entries, with each entry displayed on its own line.
     */
    //public String getLog()
    {
    //    String log = "Charity \t Hours \t Date";
    //    for (LogEntry entry: logEntries)
        {
    //        log = "\n" + entry.getCharityName() + "\t" + entry.getHours() + "\t" + entry.getDate();
        }
     //   return log;
    }

    /**
     * Gets the number of hours served at a specified charity.
     * @param charityName name of charity
     * @return hours served at specified charity
     */
    public double getSpecificCharityHours(String charityName)
    {
        int hours = 0;
        for (LogEntry entry: logEntries)
        {
            if (entry.getCharityName().equals(charityName))
            {
                hours+=entry.getHours();
            }
        }
        return hours;
    }

    public void addLogEntry(LogEntry log)
    {
        logEntries.add(log);
        totalHours += log.getHours();
    }

    public ArrayList<LogEntry> getLogEntries()
    {
        return logEntries;
    }

}
