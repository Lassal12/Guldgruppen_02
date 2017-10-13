

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    ArrayList<Swag> Swags = new ArrayList<Swag>();

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return description + ".\n" + getExitString();
    }

	private String getExitString()
    {
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString + "\n";
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
     public Swag getSwag(int index){
            return Swags.get(index);
    }
    /*
    Set a specific item in a room   
    */
    public void setSwag(Swag newSwag){
       Swag.add(newSwag);
    }
    /*
    Writing description of an item in a room
    */
    public String getRoomSwags() {
        String output = "";
        for (int i = 0; i < Swags.size(); i++) {
            output += Swags.get(i).getSwagDescription() + " ";
        }
             return output;
        }
    }


