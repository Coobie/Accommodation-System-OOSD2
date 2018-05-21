/*
 * Licensed to JGC
 */
package accommodationSystem;

/**
 * Object which holds all of the contact information
 * @author Jacob
 */
public class ContactDetails
{
    private String phoneNumber;
    private String email;
    private Address address;

    public ContactDetails(String phoneNumber, String email, Address address)
    {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public ContactDetails()
    {
        this.phoneNumber = "";
        this.email = "";
        this.address = new Address("");
    }
    
    public ContactDetails(String phoneNumber,String email)
    {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }
    
    
}
