/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio
 */
public class player {
    String name; 
    int power;
    
    public void setName(String n){
        name = n;    
    }
    public String getName(String n){
        return name; 
        
    }
    public void setPower(int p){
        power = p; 
        
    }     
    public int getPower(){
        return power; 
        
    }
}
