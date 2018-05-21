/*
 * Licensed to JGC
 */
package accommodationSystem;

/**
 * Admin can change lease options for ALL rooms across all halls
 * Admin has a user type of 3
 * @author Jacob
 */
public class Admin extends User
{
 public Admin(String username, String pass, ContactDetails contact, String firstName, String lastName)
    {
        super(username, pass, 3, contact, firstName, lastName);
    }

    @Override
    public String getUserLevel()
    {
        return "Admin";
    }
    
 
}
