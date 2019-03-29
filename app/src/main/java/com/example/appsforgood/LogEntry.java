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

    /**
     * Create an instance of Log Entry
     * @param name The name of the charity
     * @param hoursServed The number of hours volunteered
     * @param dateServed When the volunteering occurred
     * @param contactPerson The name of the contact that will be contacted in order to approve your hours
     * @param email The email of the person that should be contacted to approve your hours
     */
    public LogEntry(String name, double hoursServed, String dateServed, String contactPerson, String email)
    {
        charityName = name;
        hours = hoursServed;
        date = dateServed;
        contactName = contactPerson;
        contactEmail = email;
        validate = 0;
    }

    /**
     * Creates an empty instance of Log Entry
     */
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

    /**
     * Gets the hours this entry volunteered for
     * @return the number of hours volunteered
     */
    public double getHours()
    {
        return hours;
    }

    /**
     * Gets the date of the volunteering
     * @return the date of the volunteering
     */
    public String getDate()
    {
        return date;
    }

    /**
     * Gets the name of the contact for this volunteering
     * @return the name of the approval contact
     */
    public String getContactName()
    {
        return contactName;
    }

    /**
     * Gets the email of the approval contact for this Log Entry
     * @return the email of the contact for this Log Entry
     */
    public String getContactEmail()
    {
        return contactEmail;
    }

    /**
     * Gets the charity name for this Log Entry
     * @return the charity name for this Log Entry
     */
    public String getCharityName()
    {
        return charityName;
    }

    /**
     * Requests approval for this Log Entry (Should probably be done when initiating an instance of Log Entry)
     */
    public void requestApproval()
    {
        // Request Approval
        // Send email, and update the boolean to true when someone responds
        // If they approve (validate = 1;)
        // If they DO NOT approve (validate = -1;)
    }

    /**
     * Gets the status of approval for this Log Entry
     * -1 = NOT APPROVED
     * 0 = PENDING APPROVAL (default upon construction)
     * 1 = APPROVED
     * @return the status of approval as an int
     */
    public int getApprovalStatus()
    {
        return validate;
    }


}
