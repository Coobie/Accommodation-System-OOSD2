/*
 * Licensed to JGC
 */
package accommodationSystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for lease to display in past leases
 * 
 * @author Jacob
 */
public class PastLeaseRow
{
    private Lease lease;
    private final static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

    public PastLeaseRow(Lease lease)
    {
        this.lease = lease;
    }
    
    public String getLeaseNumber()
    {
        return lease.getLeaseNumber();
    }
    
    public String getStartDate()
    {
        return format1.format(lease.getStartLease());
    }
    
    public String getEndDate()
    {
        
        Date start = lease.getStartLease();
        Date end;
        Calendar c = Calendar.getInstance();
        c.setTime(lease.getStartLease());
        c.add(Calendar.MONTH, lease.getDuration());
        end = c.getTime();
        return format1.format(end);
    }
    
    public int getDuration()
    {
        return lease.getDuration();
    }
    
    public String getStudentName()
    {
        return lease.getStudent().getFirstName() +" "+ lease.getStudent().getLastName();
    }
    
    public String getStudentNumber()
    {
        return lease.getStudent().getStudentNumber();
    }
}
