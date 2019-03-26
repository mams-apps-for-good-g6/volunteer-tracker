package com.example.appsforgood;

public class LogEntry
{

    // Data
    private String charityName;
    private double hours;
    private String date;
    private String contactName;
    private String contactEmail;
    private int validate; // 0 = PENDING, 1 = APPROVED, -1 = NOT APPROVED

    // Constructor

    public LogEntry(String name, double hoursServed, String dateServed, String contactPerson, String email)
    {
        charityName = name;
        hours = hoursServed;
        date = dateServed;
        contactName = contactPerson;
        contactEmail = email;
        validate = 0;
    }

    public LogEntry()
    {
        charityName = "";
        hours = 0;
        date = "";
        contactName = "";
        contactEmail = "";
        validate = 0;
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
        // Send email, and update the boolean to true when someone responds
        // If they approve (validate = 1;)
        // If they DO NOT approve (validate = -1;)
    }

    public int getApprovalStatus()
    {
        return validate;
    }


}
