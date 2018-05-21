/*
 * Licensed to JGC
 */
package accommodationSystem;

/**
 * Object which holds the hall and room which is a row in the tableViews
 * 
 * @author Jacob
 */
public class RowView
{
    private Hall hall;
    private Room room;
    
    public RowView(Hall hall,Room room)
    {
        this.hall = hall;
        this.room = room;      
    }
    
    public RowView()
    {
        
    }

    public String getLeaseNumber()
    {
        if (room.isOccupied())
        {
            return this.room.getCurrentLease().getLeaseNumber();
        }
        return "";
    }

    public String getHallName()
    {
        return this.hall.getName();
    }

    public String getHallNumber()
    {
        return this.hall.getNumber();
    }

    public String getRoomNumber()
    {
        return this.room.getNumber();
    }

    public String getStudentName()
    {
        if (room.isOccupied())
        {
            return this.room.getCurrentLease().getStudent().getFullName();
        }
        return "";
    }

    public String getOccupancy()
    {
        if(this.room.isOccupied())
        {
            return "Occupied";
        }
        return "Unoccupied";
    }

    public String getCleaningStatus()
    {
        return this.room.getCleaningStatus();
    }

    public Hall getHall()
    {
        return hall;
    }

    public Room getRoom()
    {
        return room;
    }

    

    @Override
    public String toString()
    {
        return "RowView{" + "leaseNumber=" + this.getLeaseNumber() + ", hallName=" + this.getHallName() + ", hallNumber=" + this.getHallNumber() + ", roomNumber=" + this.getRoomNumber() + ", studentName=" + this.getStudentName() + ", occupancy=" + this.getOccupancy() + ", cleaningStatus=" + this.getCleaningStatus() + '}';
    }
 
    
    
}
