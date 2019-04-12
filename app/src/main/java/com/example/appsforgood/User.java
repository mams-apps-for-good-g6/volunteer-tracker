package com.example.appsforgood;

/**
 * Creates the User superclass, which is the parent of Advisor and Volunteer.
 */
public class User
{
    // DATA
    private String firstName;
    private String lastName;
    private String email;

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

    public void createNewPost()
    {
        // NEED TO IMPLEMENT
    }


}
