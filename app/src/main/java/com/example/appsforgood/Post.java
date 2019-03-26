package com.example.appsforgood;

import java.util.Date;

public class Post {
    //Data
    String organizationName;
    String description;
    String location;
    String postDate;
    String eventDate;
    String owner;

    //Constructors
    public Post(String nameOfOrganization, String eventDescription, String eventLocation, String datePosted, String dateOfEvent)
    {
        organizationName = nameOfOrganization;
        description = eventDescription;
        location = eventLocation;
        postDate = datePosted;
        eventDate = dateOfEvent;
    }
    //Methods
    public String getOrganizatioName() {return organizationName;}
    public String getDescription() {return description;}
    public String getLocation() {return location;}
    public String getPostDate() {return postDate;}
    public String getEventDate() {return eventDate;}
    public String getOwner() {return owner;}
    }
}
