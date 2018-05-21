/*
 * Licensed to JGC
 */
package accommodationSystem;

import java.util.ArrayList;

/**
 * Address class
 * An Address has to have a postcode, additional lines are added afterwards 
 * @author Jacob
 */
public class Address
{
    private ArrayList<String> lines = new ArrayList<>();
    private String postCode;

    public Address(String postCode)
    {
        this.postCode = postCode;
    }
        
    public ArrayList<String> getLines()
    {
        return lines;
    }
    
    public void addLine(String line)
    {
        lines.add(line);
    }

    public void setLines(ArrayList<String> lines)
    {
        this.lines = lines;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    @Override
    public String toString()
    {
        String out = "";
        for (String s: this.getLines())
        {
            out+= s+"\n";
        }
        out+=this.getPostCode();
        return out;
    }
    
    
}
