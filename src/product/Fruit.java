package product;

public class Fruit extends Product implements Vendable{
    
    public Fruit(String description, double price){
        super(description, price);
    }

    @Override
    public String describe(){
        return "This is fruit, it is a good choice.";
    } 


}