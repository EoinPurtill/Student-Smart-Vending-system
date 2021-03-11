package product;

import java.util.ArrayList;

import product.Snack;

public class Deal{
    private static final double maxDiscount = 100.0;
    private static final double minDiscount = 1.0;
    private String description;
    private int amountTreats, amountDrinks;
    private int amountFruit, amountSandwiches, amountSnacks;
    private double discountPercent;
    private ArrayList<Product> treats, drinks;
    private ArrayList<Product> fruits, sandwiches, snacks;

    public Deal(String description, int amountTreats, int amountDrinks,
                int amountFruit, int amountSandwiches,int amountSnacks, double discountPercent){
        this.description = description;
        this.amountTreats = amountTreats;
        this.amountDrinks = amountDrinks;
        this.amountFruit = amountFruit;
        this.amountSandwiches = amountSandwiches;
        this.amountSnacks = amountSnacks;
        this.discountPercent = Math.min(discountPercent, maxDiscount);
        this.discountPercent = Math.max(discountPercent, minDiscount);
        this.treats = new ArrayList<Product>();
        this.drinks = new ArrayList<Product>();
        this.fruits = new ArrayList<Product>();
        this.sandwiches = new ArrayList<Product>();
        this.snacks = new ArrayList<Product>();
    }

    public void addItem(Product prod){
        if(prod instanceof Treat)
            addTreat(prod);
        if(prod instanceof Drink)
            addDrink(prod);
        if(prod instanceof Fruit)
            addFruit(prod);
        if(prod instanceof Sandwich)
            addSandwich(prod);
        if(prod instanceof Snack)
            addSnack(prod);
    }

    private void addTreat(Product treat){
        if(amountTreats == 0)
            System.out.println("No treats in this offer.");
        else if(treats.size() < amountTreats)
            treats.add(treat);
        else
            System.out.println(amountTreats + "/" + amountTreats + " already selected.");
    }

    private void addDrink(Product drink){
        if(amountDrinks == 0)
            System.out.println("No drinks in this offer.");
        else if(drinks.size() < amountDrinks)
            drinks.add(drink);
        else
            System.out.println(amountDrinks + "/" + amountDrinks + " already selected.");
    }

    private void addFruit(Product fruit){
        if(amountFruit == 0)
            System.out.println("No fruit in this offer.");
        else if(fruits.size() < amountFruit)
            fruits.add(fruit);
        else
            System.out.println(amountFruit + "/" + amountFruit + " already selected.");
    }

    private void addSandwich(Product sandwich){
        if(amountSandwiches == 0)
            System.out.println("No sandwiches in this offer.");
        else if(sandwiches.size() < amountSandwiches)
            sandwiches.add(sandwich);
        else
            System.out.println(amountSandwiches + "/" + amountSandwiches + " already selected.");
    }

    private void addSnack(Product snack){
        if(amountSnacks == 0)
            System.out.println("No snacks in this offer.");
        else if(sandwiches.size() < amountSnacks)
            sandwiches.add(snack);
        else
            System.out.println(amountSnacks + "/" + amountSnacks + " already selected.");
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
        for (Product snack : snacks){
            price += snack.getPrice();
        }
        return price - ( price * (discountPercent / 100) );
    }

    public boolean isComplete(){
        return(amountTreats==treats.size() && amountDrinks==drinks.size()
        && amountFruit==fruits.size() && amountSandwiches==sandwiches.size() && amountSnacks==snacks.size());
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

    public ArrayList<Product> getSandwiches(){
        return sandwiches;
    }

    public ArrayList<Product> getSnacks(){
        return snacks;
    }

    public double getDiscount(){
        return discountPercent;
    }
}