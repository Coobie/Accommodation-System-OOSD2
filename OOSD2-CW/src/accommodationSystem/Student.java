/*
 * Licensed to JGC
 */
package accommodationSystem;

/**
 * Student class
 * A Lease has a Student
 * Student has ContactDetails
 * @author Jacob
 */
public class Student 
{
    private String firstName;
    private String lastName;
    private String studentNumber;
    private ContactDetails contactDetails;

    public Student()
    {
    }

    public Student(String firstName, String lastName, String studentNumber, ContactDetails contactDetails)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
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

    public String getStudentNumber()
    {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber)
    {
        this.studentNumber = studentNumber;
    }

    public ContactDetails getContactDetails()
    {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails)
    {
        this.contactDetails = contactDetails;
    }
    
    public String getFullName()
    {
        return firstName +" "+lastName;
    }
    
    @Override
    public String toString()
    {
        return firstName +" "+lastName + " " + studentNumber;
    }
    
}
