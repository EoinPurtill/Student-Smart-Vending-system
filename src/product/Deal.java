package product;

import java.util.ArrayList;

public class Deal{
    private static final double maxDiscount = 100.0;
    private static final double minDiscount = 1.0;
    private String description;
    private int amountTreats, amountDrinks, amountSnacks;
    private int amountFruit, amountSandwiches;
    private double discountPercent;
    private ArrayList<Product> treats, drinks, snacks;
    private ArrayList<Product> fruits, sandwiches;

    public Deal(String description, int amountTreats, int amountDrinks, int amountSnacks,
                int amountFruit, int amountSandwiches, double discountPercent){
        this.description = description;
        this.amountTreats = amountTreats;
        this.amountDrinks = amountDrinks;
        this.amountSnacks = amountSnacks;
        this.amountFruit = amountFruit;
        this.amountSandwiches = amountSandwiches;
        this.discountPercent = Math.min(discountPercent, maxDiscount);
        this.discountPercent = Math.max(discountPercent, minDiscount);
        this.treats = new ArrayList<Product>();
        this.drinks = new ArrayList<Product>();
        this.fruits = new ArrayList<Product>();
        this.sandwiches = new ArrayList<Product>();
        this.snacks = new ArrayList<Product>();
    }

    public Deal(String description, int amountTreats, int amountDrinks, int amountSnacks,
                int amountFruit, int amountSandwiches, double discountPercent,
                ArrayList<Product> treats, ArrayList<Product> drinks, ArrayList<Product> snacks,
                ArrayList<Product> fruits, ArrayList<Product> sandwiches){
        this.description = description;
        this.amountTreats = amountTreats;
        this.amountDrinks = amountDrinks;
        this.amountSnacks = amountSnacks;
        this.amountFruit = amountFruit;
        this.amountSandwiches = amountSandwiches;
        this.discountPercent = Math.min(discountPercent, maxDiscount);
        this.discountPercent = Math.max(discountPercent, minDiscount);
        this.treats = treats;
        this.drinks = drinks;
        this.fruits = fruits;
        this.sandwiches = sandwiches;
        this.snacks = snacks;
    }

    public boolean addItem(Product prod){
        if(prod instanceof Treat)
            return addTreat(prod);
        if(prod instanceof Drink)
            return addDrink(prod);
        if(prod instanceof Fruit)
            return addFruit(prod);
        if(prod instanceof Sandwich)
            return addSandwich(prod);
        if(prod instanceof Snack)
            return addSnack(prod);
        return false;
    }

    private boolean addTreat(Product treat){
        if(amountTreats == 0){
            System.out.println("No treats in this offer.");
            return false;
        }
        else if(treats.size() < amountTreats){
            treats.add(treat);
            return true;
        }
        else{
            System.out.println(amountTreats + "/" + amountTreats + " already selected.");
            return false;
        }
    }

    private boolean addDrink(Product drink){
        if(amountDrinks == 0){
            System.out.println("No drinks in this offer.");
            return false;
        }
        else if(drinks.size() < amountDrinks){
            drinks.add(drink);
            return true;
        }
        else{
            System.out.println(amountDrinks + "/" + amountDrinks + " already selected.");
            return false;
        }
    }

    private boolean addFruit(Product fruit){
        if(amountFruit == 0){
            System.out.println("No fruit in this offer.");
            return false;
        }
        else if(fruits.size() < amountFruit){
            fruits.add(fruit);
            return true;
        }
        else{
            System.out.println(amountFruit + "/" + amountFruit + " already selected.");
            return false;
        }
    }

    private boolean addSandwich(Product sandwich){
        if(amountSandwiches == 0){
            System.out.println("No sandwiches in this offer.");
            return false;
        }
        else if(sandwiches.size() < amountSandwiches){
            sandwiches.add(sandwich);
            return true;
        }
        else{
            System.out.println(amountSandwiches + "/" + amountSandwiches + " already selected.");
            return false;
        }
    }

    private boolean addSnack(Product snack){
        if(amountSnacks == 0){
            System.out.println("No snacks in this offer.");
            return false;
        }
        else if(snacks.size() < amountSnacks){
            snacks.add(snack);
            return true;
        }
        else{
            System.out.println(amountSnacks + "/" + amountSnacks + " already selected.");
            return false;
        }
    }

    public double getPrice(){
        double price = 0.0;
        for (Product treat : treats){
            price += treat.getPrice();
        }
        for (Product drink : drinks){
            price += drink.getPrice();
        }
        for (Product fruit : fruits){
            price += fruit.getPrice();
        }
        for (Product sandwich : sandwiches){
            price += sandwich.getPrice();
        }
        for (Product snack : sandwiches){
            price += snack.getPrice();
        }
        return price - ( price * (discountPercent / 100.0) );
    }

    public boolean isComplete(){
        return(amountTreats==treats.size() && amountDrinks==drinks.size()
        && amountFruit==fruits.size() && amountSandwiches==sandwiches.size());
    }

    public String toString(){
        String details = "Includes:\n";

        if(amountTreats > 0)
            details += amountTreats + " treats\n";
        if(amountDrinks > 0)
        details += amountDrinks + " drinks\n";
        if(amountFruit > 0)
        details += amountFruit + " fruit\n";
        if(amountSandwiches > 0)
        details += amountSandwiches + " sandwiches\n";
        if(amountSnacks > 0)
        details += amountSnacks + " snacks\n";

        return String.format("%s\n%s", description, details);
    }

    public void clearDeal(){
        treats.clear(); drinks.clear(); snacks.clear();
        fruits.clear(); sandwiches.clear();
    }

    public String getDescription(){
        return description;
    }

    public ArrayList<Product> getTreats(){
        return treats;
    }

    public ArrayList<Product> getDrinks(){
        return drinks;
    }

    public ArrayList<Product> getFruits(){
        return fruits;
    }

    public ArrayList<Product> getSnacks(){
        return snacks;
    }

    public ArrayList<Product> getSandwiches(){
        return sandwiches;
    }

    public int getAmountFruit(){
        return amountFruit;
    }

    public int getAmountSnacks(){
        return amountSnacks;
    }

    public int getAmountDrinks(){
        return amountDrinks;
    }

    public int getAmountSandwiches(){
        return amountSandwiches;
    }

    public int getAmountTreats(){
        return amountTreats;
    }

    public double getDiscount(){
        return discountPercent;
    }
}