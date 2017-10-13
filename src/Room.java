
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

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
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

    //Her skriver vi de ting som skal printes til skærmen ved starten af hvert rum.
    private String getExitString() {
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        returnString += "\nSwagting i rummet:\n";
        returnString += getRoomSwags();
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public Swag getSwag(int index) {
        return swags.get(index);
    }

    //Setter metoden bruges til at indsætte et specifikt item til rummet.
    public void setSwag(Swag newSwag){
       swags.add(newSwag);
    }
    
    //Beskriver hvilke ting der er i rummet.
    public String getRoomSwags() {
        String output = "";
        for (int i = 0; i < swags.size(); i++) {
            output += swags.get(i).getSwagDescription() + " ";
        }
        return output;
    }
}
