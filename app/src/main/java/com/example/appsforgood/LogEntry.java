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
    }


    // Constructor

    /**
     * Create an instance of Log Entry
     * @param name The name of the charity
     * @param hoursServed The number of hours volunteered
     * @param dateServed When the volunteering occurred
     * @param contactPerson The name of the contact that will be contacted in order to approve your hours
     * @param email The email of the person that should be contacted to approve your hours
     */
    public LogEntry(String name, double hoursServed, String dateServed, String contactPerson, String email, String p)
    {
        charityName = name;
        hours = hoursServed;
        date = dateServed;
        contactName = contactPerson;
        contactEmail = email;
        validate = 0;
        path="";
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

    public void setPath(String p)
    {
        path = p;
    }

    public String getPath()
    {
        return path;
    }

    public void setIndex(int i){index = i;}

    public int getIndex(){return index;}

    /**
     * Gets the hours this entry volunteered for
     * @return the number of hours volunteered
     */
    public double getHours()
    {
        return hours;
    }

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

    public String getStringApprovalStatus()
    {
        return Integer.toString(validate);
    }

    public static ArrayList<LogEntry> createLogEbntries(int numContacts) {
        ArrayList<LogEntry> contacts = new ArrayList<LogEntry>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new LogEntry("name", (i/1.0), "date", "contact person", "email", ""));
        }

        return contacts;
    }
}
