package product;

public class Drink extends Product implements Vendable{

    public Drink(String description, double price){
        super(description, price);
    }

    @Override
    public String describe(){
        return "This is a drink, time to cool off.";
    } 


}