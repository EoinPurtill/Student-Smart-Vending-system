package product;

public class ProductDescriptorFacade {

    private Drink drink;
    private Fruit fruit;
    private Sandwich sandwich;
    private Snack snack;
    private Treat treat;

    
    //tie in product factory
    
    
    //Parameterise the constructor to reference business tier
    //sonarqube
    //jdepends plugin to compute martin's metric.
    //documentation to be on point!
    //team contribution documentd 


    public ProductDescriptorFacade(){
        drink       = new Drink("Drink", 0.0);
        fruit       = new Fruit("Fruit", 0.0);
        sandwich    = new Sandwich("Sandwich", 0.0);
        snack       = new Snack("Snack", 0.0);
        treat       = new Treat("Treat", 0.0); 
        
    }

    public String describe(){
        String description = "";
        description += drink.describe();
        description += "\n";
        description += fruit.describe();
        description += "\n";
        description += sandwich.describe();
        description += "\n";
        description += snack.describe();
        description += "\n";
        description += treat.describe();
        
        return description;
        
    }

}
