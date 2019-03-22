package com.example.appsforgood;

public class LogEntry
{

    // Data
    private String charityName;
    private double hours;
    private String date;
    private String contactName;
    private String contactEmail;

    // Constructor

    public LogEntry(String name, double hoursServed, String dateServed, String contactPerson, String email)
    {
        charityName = name;
        hours = hoursServed;
        date = dateServed;
        contactName = contactPerson;
        contactEmail = email;
    }

    public LogEntry()
    {
        charityName = "";
        hours = 0;
        date = "";
        contactName = "";
        contactEmail = "";
    }

    // Methods

    public double getHours()
    {
        return hours;
    }

    public String getDate()
    {
        return date;
    }

    public String getContactName()
    {
        return contactName;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public String getCharityName()
    {
        return charityName;
    }

    public void requestApproval()
    {
        // Request Approval
    }

    public boolean getApprovalStatus()
    {
        return true;
        // Return boolean that's related to request approval
    }


}
