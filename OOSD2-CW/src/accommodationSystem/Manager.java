/*
 * Licensed to JGC
 */
package accommodationSystem;

/**
 * Manager can change lease options for ALL rooms across all halls
 * Manager has a user type of 2
 * @author Jacob
 */
public class Manager extends User
{
    
    public Manager(String username, String pass, ContactDetails contact, String firstName, String lastName)
    {
        super(username, pass, 2, contact, firstName, lastName);
    }

    @Override
    public String getUserLevel()
    {
        return "Manager";
    }
    
    
    
}
