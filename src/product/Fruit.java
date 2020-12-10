package product;

public class Fruit extends Product implements Vendable{

    @Override
    public String describe(){
        return "This is fruit, it is a good choice.";
    } 


}