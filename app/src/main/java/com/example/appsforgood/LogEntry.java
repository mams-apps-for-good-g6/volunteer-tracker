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

    public LogEntry(String n, double h, String d, String cN, String cE)
    {
        charityName = n;
        hours = h;
        date = d;
        contactName = cN;
        contactEmail = cE;
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
