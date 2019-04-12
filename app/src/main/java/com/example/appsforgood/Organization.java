package com.example.appsforgood;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Random;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class Organization
{
    // This is push V2 2:18 3/25/19

    // Data
    private String code;
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
        code = Integer.toString(rand.nextInt(8999) + 1000);
    }

    public Organization()
    {
        code = ""; //generate random String
        name = "";
        advisor = new User("", "", "");
        volunteers = new ArrayList<>();
    }

    // Methods

    public String toString()
    {
        return "name: " + name + " code: " + code;
    }


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

    public String getCode()
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
            r = r + volunteers.get(i).getFullName() + " - " + volunteers.get(i).getTotalHours() + "\n";
        }

        return r;
    }

    public String getStudentLog(int number)
    {
        return "Name: " + volunteers.get(number).getFullName() + "\n" + "Email: " + volunteers.get(number).getEmail() + "\n" + "Total Hours: " + volunteers.get(number).getTotalHours();
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