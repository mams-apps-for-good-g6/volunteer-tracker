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
    public Organization(String organizationName, User advisorName)
    {
        name = organizationName;
        advisor = advisorName;
        volunteers = new ArrayList<>();

        Random rand = new Random();
        code = rand.nextInt(8999) + 1000;
    }

    public Organization()
    {
        code = 0; //generate random String
        name = "";
        advisor = new User("", "", "");
        volunteers = new ArrayList<>();
    }

    // Methods

    public void addVolunteer(Volunteer v)
    {
        volunteers.add(v);
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

    public int getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public User getAdvisor()
    {
        return advisor;
    }

    public String getStudentList()
    {
        String r = new String("");

        for(int i = 0; i < volunteers.size(); i++)
        {
            r += volunteers.get(i).getFullName() + ": " + volunteers.get(i).getTotalHours() + " hours \n";
        }

        return r;
    }

    public String getStudentLog(Volunteer volunteer)
    {
        return volunteer.getLog();
    }

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
