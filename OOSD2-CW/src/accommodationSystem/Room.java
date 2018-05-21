/*
 * Licensed to JGC
 */
package accommodationSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Room object - individual room in a hall
 * Room is in one hall
 * Room has a Lease
 * @author Jacob
 */
public class Room 
{
    private String number;
    private double monthlyRent;
    private List<Lease> leases = new ArrayList<>();
    private boolean occupancyStatus;
    private String cleaningStatus;

    public Room(String number, double monthlyRent, String cleaningStatus)
    {
        this.number = number;
        this.monthlyRent = monthlyRent;
        this.cleaningStatus = cleaningStatus;
    }
    
    public Room()
    {
        
    }

    public Room(String number, double monthlyRent, Lease lease, boolean occupancyStatus, String cleaningStatus)
    {
        this.number = number;
        this.monthlyRent = monthlyRent;
        this.leases.add(lease);
        this.occupancyStatus = occupancyStatus;
        this.cleaningStatus = cleaningStatus;
    }
    
    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public double getMonthlyRent()
    {
        return monthlyRent;
    }

    public void setMonthlyRent(double monthlyRent)
    {
        this.monthlyRent = monthlyRent;
    }

    public Lease getCurrentLease()
    {
        for (Lease lease : leases)
        {
            if (lease.isCurrent())
            {
                return lease;
            }
        }
        return null;
    }

    public List<Lease> getLeases()
    {
        return leases;
    }

    public void setLeases(List<Lease> leases)
    {
        this.leases = leases;
    }
    
    public void setLease(Lease lease)
    {
        lease.setCurrent(true);
        this.leases.add(lease);
        this.setOccupancyStatus(true);
    }
    
    public void updateLease(Lease lease)
    {
        lease.setCurrent(true);
        this.leases.remove(this.getCurrentLease());
        this.leases.add(lease);
    }
    
    public void removeLease()
    {
        this.getCurrentLease().setCurrent(false);
        this.setOccupancyStatus(false);
    }

    public boolean isOccupied()
    {
        return occupancyStatus;
    }
    
    public void setOccupancyStatus(boolean occupancyStatus)
    {
        if (! this.getCleaningStatus().equals("Off-line")) //Only allow if cleaning status if not offline
        {
            this.occupancyStatus = occupancyStatus;
        }
    }

    public String getCleaningStatus()
    {
        return cleaningStatus;
    }

    public void setCleaningStatus(String cleaningStatus)
    {
        this.cleaningStatus = cleaningStatus;
    }
    
}
