
class Swag {

   
    @Override
    public String toString() {
        return "Swag{" + "swagDescription=" + swagDescription + '}';
    }

    static Object get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    String swagDescription;
    
    //Der oprettes en streng som kan indeholde navnet til items(swag) i spillet.
    public Swag(String newSwagDescription) {
        swagDescription = newSwagDescription;
    }
    
    public String getSwagDescription() {
        return swagDescription;
    }

    Object getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
