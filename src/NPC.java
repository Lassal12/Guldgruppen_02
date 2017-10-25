import java.util.HashMap;
import java.util.Set;


public class NPC {
    private String name;
    private String greeting;
    private HashMap<String, Swag> inventory;
    
    
    public NPC(String name, String greeting) {
        this.name = name;
        this.greeting = greeting;
        inventory = new HashMap<String, Swag>();
    }
    
    public String getName() {
        return name;
    }
    
    public String getGreeting() {
        return name + ": " + greeting + "\n";
    }
    
    public Swag getSwag(String swag) {
        return inventory.get(swag);
    }
    
    public void addSwagToInventory(Swag swag) {
        inventory.put(swag.getSwagDescription(), swag);
    }
    
    public void removeSwagFromInventory(String swag) {
        inventory.remove(swag);
    }
    
    public boolean isInInventory(String swagName) {
        return inventory.containsKey(swagName);
    }
    
    public void setGreeting(String response) {
        this.greeting = response;
    }
}
