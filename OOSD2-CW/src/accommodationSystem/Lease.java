/*
 * Licensed to JGC
 */
package accommodationSystem;

import java.util.Date;

/**
 * Lease class
 * A Room may have many Leases
 * A Room has a current Lease
 * A Lease has a Student
 * @author Jacob
 */
public class Lease 
{
    private Student student;
    private String leaseNumber;
    private int duration;
    private Date startLease;
    private boolean current;

    public Lease(){}
    
    public Lease(Student student, String leaseNumber, int duration, Date startLease)
    {
        this.student = student;
        this.leaseNumber = leaseNumber;
        this.duration = duration;
        this.startLease = startLease;
    }

    public Lease(Student student, String leaseNumber, int duration, Date startLease, boolean current)
    {
        this.student = student;
        this.leaseNumber = leaseNumber;
        this.duration = duration;
        this.startLease = startLease;
        this.current = current;
    }
    
    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public String getLeaseNumber()
    {
        return leaseNumber;
    }

    public void setLeaseNumber(String leaseNumber)
    {
        this.leaseNumber = leaseNumber;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public Date getStartLease()
    {
        return startLease;
    }

    public void setStartLease(Date startLease)
    {
        this.startLease = startLease;
    }

    public boolean isCurrent()
    {
        return current;
    }

    public void setCurrent(boolean current)
    {
        this.current = current;
    }
    

    @Override
    public String toString()
    {
        return "Lease{" + "student=" + student + ", leaseNumber=" + leaseNumber + ", duration=" + duration + ", startLease=" + startLease + '}';
    }
        
}
