package com.example.appsforgood;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class LogEntry implements Parcelable
{

    // Data
    private String charityName;
    private double hours;
    private String date;
    private String contactName;
    private String contactEmail;
    private int validate; // 0 = PENDING, 1 = APPROVED, -1 = NOT APPROVED
    private String path;
    private int index;
    private String volunteerName;

    // Classes associated with sending an ArrayList of LogEntries thorugh an intent

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(charityName);
        out.writeDouble(hours);
        out.writeString(date);
        out.writeString(contactName);
        out.writeString(contactEmail);
        out.writeInt(validate);
        out.writeString(path);
        out.writeInt(index);
        out.writeString(volunteerName);
    }

    public static final Parcelable.Creator<LogEntry> CREATOR = new Parcelable.Creator<LogEntry>() {
        public LogEntry createFromParcel(Parcel in) {
            return new LogEntry(in);
        }

        public LogEntry[] newArray(int size) {
            return new LogEntry[size];
        }
    };

    private LogEntry(Parcel in) {
        charityName = in.readString();
        hours = in.readDouble();
        date = in.readString();
        contactName = in.readString();
        contactEmail = in.readString();
        validate = in.readInt();
        path = in.readString();
        index = in.readInt();
        volunteerName = in.readString();
    }


    // Constructors

    /**
     * Create an instance of Log Entry
     * @param name The name of the charity
     * @param hoursServed The number of hours volunteered
     * @param dateServed When the volunteering occurred
     * @param contactPerson The name of the contact that will be contacted in order to approve your hours
     * @param email The email of the person that should be contacted to approve your hours
     */
    public LogEntry(String name, double hoursServed, String dateServed, String contactPerson, String email, String p, String volunteer)
    {
        charityName = name;
        hours = hoursServed;
        date = dateServed;
        contactName = contactPerson;
        contactEmail = email;
        validate = 0;
        path="";
        volunteerName = volunteer;
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
        path="";
    }

    // Methods

    /**
     * Sets the name associated with this log entry
     * @param name volunteer's name
     */
    public void setVolunteerName(String name) {volunteerName = name;}

    /**
     * Sets the name associated with this log entry
     * @return volunteer's name
     */
    public String getVolunteerName() {return volunteerName;}

    /**
     * Sets the path to this log entry
     * @param p the path, including the organization, volunteer, volunteer index, and log entry index
     */
    public void setPath(String p)
    {
        path = p;
    }

    /**
     * Gets the path to this log entry
     * @return path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Sets the index of this log entry in the volunteer's ArrayList of log entries
     * @param i index
     */
    public void setIndex(int i){index = i;}

    /**
     * Gets the index of this log entry in the volunteer's ArrayList of log entries
     * @return index
     */
    public int getIndex(){return index;}

    /**
     * Gets the hours this entry volunteered for
     * @return the number of hours volunteered
     */
    public double getHours()
    {
        return hours;
    }

    /**
     * Gets the hours associated with this entry as a string
     * @return string hours
     */
    public String getStringHours() {return Double.toString(hours);}

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

    /**
     * gets the approval status as a string
     * -1 = NOT APPROVED
     * 0 = PENDING APPROVAL (default upon construction)
     * 1 = APPROVED
     * @return approval status as a string
     */
    public String getStringApprovalStatus()
    {
        return Integer.toString(validate);
    }

    /**
     * Sets the approval status of this entry
     * -1 = NOT APPROVED
     * 0 = PENDING APPROVAL (default upon construction)
     * 1 = APPROVED
     * @param newInt approval status
     */
    public void setApprovalStatus(Integer newInt)
    {
        validate = newInt;
    }

}
