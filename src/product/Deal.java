package product;

import java.util.ArrayList;

public class Deal{
    private static final double maxDiscount = 100.0;
    private static final double minDiscount = 1.0;
    private String description;
    private int amountTreats, amountDrinks;
    private int amountFruit, amountSandwiches;
    private double discountPercent;
    private ArrayList<Product> treats, drinks;
    private ArrayList<Product> fruits, sandwiches;

    public Deal(String description, int amountTreats, int amountDrinks,
                int amountFruit, int amountSandwiches, double discountPercent){
        this.description = description;
        this.amountTreats = amountTreats;
        this.amountDrinks = amountDrinks;
        this.amountFruit = amountFruit;
        this.amountSandwiches = amountSandwiches;
        this.discountPercent = Math.min(discountPercent, maxDiscount);
        this.discountPercent = Math.max(discountPercent, minDiscount);
        this.treats = new ArrayList<Product>();
        this.drinks = new ArrayList<Product>();
        this.fruits = new ArrayList<Product>();
        this.sandwiches = new ArrayList<Product>();
    }

    public void addTreat(Product treat){
        if(amountTreats == 0)
            System.out.println("No treats in this offer.");
        else if(treats.size() < amountTreats)
            treats.add(treat);
        else
            System.out.println(amountTreats + "/" + amountTreats + " already selected.");
    }

    public void addDrink(Product drink){
        if(amountDrinks == 0)
            System.out.println("No drinks in this offer.");
        else if(drinks.size() < amountTreats)
            drinks.add(drink);
        else
            System.out.println(amountDrinks + "/" + amountDrinks + " already selected.");
    }

    public void addFruit(Product fruit){
        if(amountFruit == 0)
            System.out.println("No fruit in this offer.");
        else if(fruits.size() < amountFruit)
            fruits.add(fruit);
        else
            System.out.println(amountFruit + "/" + amountFruit + " already selected.");
    }

    public void addSandwich(Product sandwich){
        if(amountSandwiches == 0)
            System.out.println("No sandwiches in this offer.");
        else if(sandwiches.size() < amountSandwiches)
            sandwiches.add(sandwich);
        else
            System.out.println(amountSandwiches + "/" + amountSandwiches + " already selected.");
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
        return price - ( price * (discountPercent / 100) );
    }

    public boolean isComplete(){
        return(amountTreats==treats.size() && amountDrinks==drinks.size()
        && amountFruit==fruits.size() && amountSandwiches==sandwiches.size());
    }

    public String toString(){
        return "Deal.java: toString() PLACEHOLDER";
    }
}