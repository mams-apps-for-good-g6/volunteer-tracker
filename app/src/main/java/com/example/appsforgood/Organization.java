package com.example.appsforgood;

import java.util.ArrayList;
import java.util.Random;

public class Organization
{
    // This is push V2 2:18 3/25/19

    // Data
    private int code;
    private String name;
    private User advisor;
    private ArrayList<Volunteer> volunteers;

    // Constructors

    /**
     * Constructs an organization with an organization name, a advisor name, a unique organization code, and an array list of volunteers
     * @param organizationName the name of the organization
     * @param advisorName the name of the advisor
     */
    public Organization(String organizationName, User advisorName)
    {
        name = organizationName;
        advisor = advisorName;
        volunteers = new ArrayList<>();

        Random rand = new Random();
        code = rand.nextInt(8999) + 1000;
    }

    /**
     * Empty organization
     */
    public Organization()
    {
        code = 0; //generate random String
        name = "";
        advisor = new User("", "", "");
        volunteers = new ArrayList<>();
    }

    // Methods

    /**
     * Adds a volunteer to an organization by appending it to the array list of volunteers
     * @param v a volunteer object
     */
    public void addVolunteer(Volunteer v)
    {
        volunteers.add(v);
    }

    /**
     * Removes a volunteer from the array list of volunteers
     * @param v a volunteer object
     */
    public void removeVolunteer(Volunteer v)
    {
        int index = 0;
        for (Volunteer item: volunteers)
        {
            if (v==item)
            {
                volunteers.remove(index);
                break;
            }
            index++;
        }
    }

    /**
     * Gets the unique code of an organization used by volunteers to join an organization
     * @return the unique code of an organization used by volunteers to join an organization
     */
    public int getCode()
    {
        return code;
    }

    /**
     * Gets the name of the organization
     * @return the name of the organization
     */
    public String getOrganizationName()
    {
        return name;
    }

    /**
     * Sets the name of the organization
     * @param newName the new name of the organization
     */
    public void setName(String newName)
    {
        name = newName;
    }

    /**
     * gets the name of the advisor of the organization
     * @return the organization's advisor's name
     */
    public User getAdvisor()
    {
        return advisor;
    }

    /**
     * gets a list of all the student names with the corresponding hours
     * @return all student names with corresponding hours
     */
    public String getStudentList()
    {
        String r = new String("");

        for(int i = 0; i < volunteers.size(); i++)
        {
            r = r + volunteers.get(i).getFullName() + " - " + volunteers.get(i).getTotalHours() + "\n";
        }

        return r;
    }

    /**
     * Gets a volunteer's name, email, and total hours as a string
     * @param number the volunteer's index in the organizatio's array list of volunteers
     * @return a volunteer's name, email, and total hours as a string
     */
    public String getStudentLog(int number)
    {
        return "Name: " + volunteers.get(number).getFullName() + "\n" + "Email: " + volunteers.get(number).getEmail() + "\n" + "Total Hours: " + volunteers.get(number).getTotalHours();
    }

    /**
     * Gets the cummulative number of hours served by all students
     * @return the total number of hours served by all studnets
     */
    public double getAllTotalHours()
    {
        double total = 0;

        for(int i = 0; i < volunteers.size(); i++)
        {
            total = total + volunteers.get(i).getTotalHours();
        }

        return total;
    }
}
