package com.example.appsforgood;

import java.util.ArrayList;

public class Volunteer extends User
{
    // DATA

    private double totalHours;
    private ArrayList<LogEntry> logEntries;
    private int index;
    private String orgPath;
    private String orgName;

    // CONSTRUCTOR

    /**
     * Create a volunteer (an instance of the Volunteer class) with information provided by the volunteer
     * @param first Volunteer's first name
     * @param last Volunteer's last name
     * @param email Volunteer's email
     */
    public Volunteer(String first, String last, String email)
    {
        super(first, last, email);
        totalHours = 0;
        index = 0;
        logEntries = new ArrayList<>();
        orgPath="";
        orgName="";
    }

    /**
     * Creates a volunteer instance without any information
     */
    public Volunteer() {
        super();
        totalHours=0;
        index = 0;
        logEntries = new ArrayList<>();
        orgPath="";
        orgName="";
    }

    // METHODS

    /**
     * allows the volunteer to set the organization name
     * @param name the new organization name
     */
    public void setOrgName(String name){orgName=name;}

    /**
     * Returns the existing organization name that the volunteer belongs to
     * @return the existing organization name
     */
    public String getOrgName(){return orgName;}

    /**
     * changes the index of the volunteer within their organization's ArrayList
     * @param i the new index of the volunteer within their organization's ArrrayList
     */
    public void setIndex(int i){index = i;}

    /**
     * Gets the current index of the volunteer within their organization's ArrayList
     * @return the current index of the volunteer within their organization's ArrayList
     */
    public int getIndex(){return index;}

    /**
     *
     * @param path
     */
    public void setOrgPath(String path){orgPath=path;}

    /**
     *
     * @return
     */
    public String getOrgPath(){return orgPath;}

    /**
     * returns the total number of hours that the total number of volunteering hours (approved and unapproved?)
     * @return
     */
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

    /**
     * adds a log entry to the volunteer's ArrayList of existing log entries
     * the log entry's status is automatically set to pending
     * the log entry is appended to the end of the ArrayList
     * @param log a log with information provided by the volunteer
     */
    public void addLogEntry(LogEntry log)
    {
        logEntries.add(log);
        int index = logEntries.size()-1;
        log.setIndex(index);
        totalHours+=log.getHours();
    }

    /**
     * changes the index of a given log entry
     * @param index the new index
     * @param log an existing log entry
     */
    public void setLogEntry(int index, LogEntry log)
    {
        logEntries.set(index, log);
    }

    /**
     * Returns the volunteer's existing ArrayList with all existing entries
     * @return the volunteer's existing ArrayList with all existing entries
     */
    public ArrayList<LogEntry> getLogEntries()
    {
        return logEntries;
    }

}
