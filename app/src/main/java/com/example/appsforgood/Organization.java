package com.example.appsforgood;


import java.util.ArrayList;
import java.util.Random;


public class Organization
{

    // Data
    private String code;
    private String name;
    private User advisor;
    private ArrayList<Volunteer> volunteers;

    // Constructors

    /**
     * Constructs an organization with a given name and advisor
     * @param organizationName name of the organization
     * @param advisorName organization's advisor (an Advisor orbject)
     */
    public Organization(String organizationName, User advisorName)
    {
        name = organizationName;
        advisor = advisorName;
        volunteers = new ArrayList<>();
        Random rand = new Random();
        code = Integer.toString(rand.nextInt(8999) + 1000);
    }

    /**
     * Constructs an organization with a default name and advisor
     */
    public Organization()
    {
        code = ""; //generate random String
        name = "";
        advisor = new User("", "", "");
        volunteers = new ArrayList<>();
    }

    // Methods

    /**
     * Adds a volunteer to the organization
     * @param v the volunteer to be added
     */
    public void addVolunteer(Volunteer v)
    {
        volunteers.add(v);
        int index = volunteers.size()-1;
        v.setIndex(index);
    }

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
     * Gets the org code
     * @return organiztion code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Gets the org name
     * @return organization name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the org name
     * @param newName organization name
     */
    public void setName(String newName)
    {
        name = newName;
    }

    /**
     * Gets the org's advisor
     * @return advisor (an Advisor object)
     */
    public User getAdvisor()
    {
        return advisor;
    }

    /**
     * gets the ArrayList of volunteers
     * @return organization's ArrayList of volunteers
     */
    public ArrayList<Volunteer> getVolunteers()
    {
        return volunteers;
    }


    //public double getAllTotalHours()
    {
     //   double total = 0;

     //   for(int i = 0; i < volunteers.size(); i++)
        {
     //       total = total + volunteers.get(i).getTotalHours();
        }

     //   return total;
    }

}