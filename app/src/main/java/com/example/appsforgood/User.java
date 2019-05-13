package com.example.appsforgood;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Creates the User superclass, which is the parent of Advisor and Volunteer.
 */
public class User
{
    // DATA
    private String firstName;
    private String lastName;
    private String email;
    boolean emptyFirstName = false;
    boolean emptyLastName = false;
    boolean emptyEmail = false;
    boolean emp = false;
    String error = "Please enter";

    // CONSTRUCTORS

    /**
     * Constructs a user with a first name, last name, and email.
     * @param first user's first name
     * @param last user's last name
     * @param userEmail user's email
     */
    public User(String first, String last, String userEmail)
    {
        firstName = first;
        lastName = last;
        email = userEmail;
    }

    public User()
    {
        firstName = "";
        lastName = "";
        email = "";
    }

    // METHODS

    public String getFullName() {return firstName + " " + lastName;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public void setFirstName(String name) {firstName=name;}
    public void setLastName(String name) {lastName=name;}
    public void setEmail(String emailStr) {email=emailStr;}

    public void empty()
    {
        if(firstName.isEmpty())
        {
            Log.d("Sriya log","in first name is empty");

            emptyFirstName = true;
            emp = true;
            error = error.concat(" your first name");
        }

        if(lastName.isEmpty())
        {
            emptyLastName = true;
            emp = true;
        }

        if(email.isEmpty() || email.contains("@")==false)
        {
            emptyEmail = true;
            emp = true;

            if(emptyFirstName && emptyLastName)
            {
                error = error.concat(", your last name, and a valid email");
            }

            else if(emptyFirstName && emptyLastName == false)
            {
                error = error.concat(" and a valid email");
            }

            else if (emptyFirstName == false && emptyLastName)
            {
                error = error.concat( " your last name and a valid email");
            }

            else
            {
                error = error.concat(" a valid email");
            }
        }

        if(emptyLastName && emptyEmail == false && emptyFirstName == false)
        {
            error = error.concat(" your last name");
        }
    }

    public void emptyToast(Context context)
    {
        empty();
        if (emp)
        {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkEmpty()
    {
        empty();
        return emp;
    }

    public void createNewPost()
    {
        // future implementation
    }
}
