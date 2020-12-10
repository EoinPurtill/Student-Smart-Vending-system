package product;

public class Drink extends Product implements Vendable{

    @Override
    public String describe(){
        return "This is a drink, time to cool off.";
    } 


}