package product;

public class Treat extends Product implements Vendable{

    public Treat(String description, double price){
        super(description, price);
    }

    @Override
    public String describe(){
        return "This is a sugary chocolate treat, your Dentist may not be too happy!";
    } 


}