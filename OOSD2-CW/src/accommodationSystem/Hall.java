/*
 * Licensed to JGC
 */
package accommodationSystem;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 * Hall object (self-explanatory)
 * Hall has many rooms
 * Hall has a Warden responsible for it
 * @author Jacob
 */
public class Hall 
{
    private String name;
    private String number;
    private Address address;
    private String phoneNum;
    private List<Room> rooms = new ArrayList<>();
    private Warden warden;
    private Image image;
    

    public Hall(String name, String number, Address address, String phoneNum)
    {
        this.name = name;
        this.number = number;
        this.address = address;
        this.phoneNum = phoneNum;
        this.image = new Image("http://thedroideffect.com/wp-content/themes/thedroideffect/images/missing-image-232x150.png");
    }

    public Hall(String name, String number, Address address, String phoneNum, Image image)
    {
        this.name = name;
        this.number = number;
        this.address = address;
        this.phoneNum = phoneNum;
        this.image = image;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getPhoneNum()
    {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public List<Room> getRooms()
    {
        return rooms;
    }

    public void setRooms(List<Room> rooms)
    {
        this.rooms = rooms;
    }
    
    public void addRoom(Room room)
    {
        this.rooms.add(room);
    }

    public Warden getWarden()
    {
        return warden;
    }

    public void setWarden(Warden warden)
    {
        this.warden = warden;
    }

    public Image getImage()
    {
        return this.image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }
    
        
    @Override
    public String toString()
    {
        return "Hall{" + "name=" + name + ", number=" + number + ", address=" + address + ", phoneNum=" + phoneNum + ", rooms=" + rooms + '}';
    }
    
    
}
