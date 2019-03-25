package com.example.appsforgood;

import java.util.ArrayList;

public class Organization
{
    // This is push V2 2:18 3/25/19

    // Data
    private String code;
    private String name;
    private User advisor;
    private ArrayList<Volunteer> list;

    // Constructors
    public Organization(String n, User a)
    {
        code = ""; //generate random String
        name = n;
        advisor = a;
        list = new ArrayList<>();
    }

    public Organization()
    {
        code = ""; //generate random String
        name = "";
        advisor = new User("", "", "");
        list = new ArrayList<>();
    }

    // Methods

    public void addStudent(Volunteer s)
    {
        list.add(s);
    }

    public void removeStudent(int number)
    {
        list.remove(number);
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

        for(int i = 0; i < list.size(); i++)
        {
            r = r + list.get(i).getFullName() + " - " + list.get(i).getTotalHours() + "\n";
        }

        return r;
    }

    public String getStudentLog(int number)
    {
        return "Name: " + list.get(number).getFullName() + "\n" + "Email: " + list.get(number).getEmail() + "\n" + "Total Hours: " + list.get(number).getTotalHours();
    }

    public double getAllTotalHours()
    {
        double total = 0;

        for(int i = 0; i < list.size(); i++)
        {
            total = total + list.get(i).getTotalHours();
        }

        return total;
    }
}
