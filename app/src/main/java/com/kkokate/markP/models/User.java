package com.kkokate.markP.models;

public enum User {

    STUDENT(1, "STUDENT"),
    ORGANIZATION(2, "ORGANIZATION");

    private int id;
    private String description;

    User(int id, String description)
    {
        this.id= id;
        this.description= description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static User findById(int id)
    {
        User result= null;

        for(User userType : User.values())
        {
            if(id == userType.getId())
            {
                result= userType;
                break;
            }

        }
        if(result == null)
        {
            throw new RuntimeException("Invalid Card Type Id");
        }
        return result;
    }
}
