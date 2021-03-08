package product;

public class Sandwich extends Product implements Vendable{

    public Sandwich(String description, double price){
        super(description, price);
    }

    @Override
    public String describe(){
        return "This is a delicious, savoury sandwich. Hunger be gone!.";
    } 

}