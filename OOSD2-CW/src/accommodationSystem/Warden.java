/*
 * Licensed to JGC
 */
package accommodationSystem;

/**
 * Warden user can change cleaning/maintenance options
 * Warden has a user type of 1
 * Hall has a Warden
 * Warden can only see the rooms in their hall
 * @author Jacob
 */
public class Warden extends User
{

    public Warden(String username, String pass, ContactDetails contact, String firstName, String lastName)
    {
        super(username, pass, 1, contact, firstName, lastName);
    }

    @Override
    public String getUserLevel()
    {
        return "Warden";
    }

    
}
