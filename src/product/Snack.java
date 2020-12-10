package product;

public class Snack extends Product implements Vendable{

    public Snack(String description, double price){
        super(description, price);
    }

    @Override
    public String describe(){
        return "This is a snack, it is probably salty and tasty.";
    } 

}