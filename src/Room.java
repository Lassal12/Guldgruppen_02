
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room {

    private String description;
    private HashMap<String, Room> exits;
    //Vi opretter en ArrayList som kan indeholde de ting vi placere i de forskellige rum.
    ArrayList<Swag> swags = new ArrayList<Swag>();
    //Vi opretter et HashMap som kan indeholde npc'er som skal være i de forskellige rum.
    private HashMap<String, NPC> characters;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        characters = new HashMap<String, NPC>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return description + ".\n" + getExitString();
    }
    
    public String getMediumDescription() {
        return getExitStringToGo();
    }

    //Her skriver vi de ting som skal printes til skærmen ved starten af hvert rum.
    private String getExitString() {
        String returnString = "\nNPC'er i rummet:\n";
        returnString += getNPCString();
        returnString += "\nSwagting i rummet:\n";
        returnString += getRoomSwags() + "\n";
        returnString += "\nUdgange:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        returnString += "\n";
        return returnString;
    }
    
    private String getExitStringToGo() {
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }
/*
    Get item from the room 
    */
    public Swag getSwag(int index) {
        return swags.get(index);
        
    }
     /*
    Få items fra rummet og systemet kan kende forskel på disse 2 commands ved at se om det er et string eller index
    */
    public Swag getSwag(String SwagName) {
        for (int i = 0; i < swags.size(); i++) {
            if(swags.get(i).getSwagDescription().equals(SwagName))    {
                return swags.get(i);
            }
                      
        }
        return null;
        
    }
     /*
    Fjerner item fra rummet  
    */

    /**
     *
     * @param Swagname
     */

    public void removeSwag(String SwagName) {
        for (int i = 0; i < swags.size(); i++) {
            if(swags.get(i).getSwagDescription().equals(SwagName))    {
            swags.remove(i);
            }
                      
        }       
    }
    
    public NPC getNPC(String name) {
        return characters.get(name);
    }

    //Setter metoden bruges til at indsætte et specifikt item til rummet.
    public void setSwag(Swag newSwag){
       swags.add(newSwag);
    }
    
    //Setter motoden bruges til at indsætte en specifik npc i rummet.
    public void setNPC(String name, String description) {
        characters.put(name, new NPC(name, description));
    }
    
    //Beskriver hvilke ting der er i rummet.
    public String getRoomSwags() {
        String output = "";
        if(!swags.isEmpty()) {
        for (int i = 0; i < swags.size(); i++) {
            output += swags.get(i).getSwagDescription() + " ";
        }
        return output;
        }
        else {
            return output + "Ingen swagting!\n";
        }
    }
    
    //Beskriver hvilke npc'er der er i rummet.
    public String getNPCString() {
        String charactersString = "";
        if(!characters.isEmpty()) {
            Set<String> npcNames = characters.keySet();
            for(String npcName : npcNames) {
                charactersString += getNPC(npcName).getGreeting();
            }
            return charactersString;
        }
        else {
            return charactersString + "Ingen NPC'er!\n";
        }
    }
}
