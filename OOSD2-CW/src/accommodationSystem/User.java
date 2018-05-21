/*
 * Licensed to JGC
 */
package accommodationSystem;

/**
 * User is abstract
 * Warden and Manager are Users
 * User has ContactDetails
 * @author Jacob
 */
public abstract class User
{
    private String username;
    private String pass;
    private int userType;
    private ContactDetails contactDetails;
    private String firstName;
    private String lastName;

    public User(String username, String pass, int userType, ContactDetails contactDetails, String firstName, String lastName)
    {
        this.username = username;
        this.pass = pass;
        this.userType = userType;
        this.contactDetails = contactDetails;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public int getUserType()
    {
        return userType;
    }

    public void setUserType(int userType)
    {
        this.userType = userType;
    }

    public ContactDetails getContactDetails()
    {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails)
    {
        this.contactDetails = contactDetails;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    abstract public String getUserLevel();
    
}
