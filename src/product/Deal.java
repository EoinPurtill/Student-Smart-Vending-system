package product;

import java.util.ArrayList;

public class Deal{
    private static final int TREATS = 0, DRINKS = 1, SNACKS = 2;
	private static final int FRUIT = 3, SANDWICHES = 4;
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

    public int addItem(Product prod){
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
        return -1;
    }

    public String removeItem(int itemType){
        switch(itemType){
            case TREATS:        if(treats.size() > 0){
                                    String s =  treats.get(treats.size() - 1).getDescription() + " removed from deal\n";
                                    treats.remove(treats.size() - 1);
                                    return s;
                                }
                                break;
            case DRINKS:        if(drinks.size() > 0){
                                    String s =  drinks.get(drinks.size() - 1).getDescription() + " removed from deal\n";
                                    drinks.remove(drinks.size() - 1);
                                    return s;
                                }
                                break;
            case FRUIT:         if(fruits.size() > 0){
                                    String s =  fruits.get(fruits.size() - 1).getDescription() + " removed from deal\n";
                                    fruits.remove(fruits.size() - 1);
                                    return s;
                                }
                                break;
            case SNACKS:        if(snacks.size() > 0){
                                    String s =  snacks.get(snacks.size() - 1).getDescription() + " removed from deal\n";
                                    snacks.remove(snacks.size() - 1);
                                    return s;
                                }
                                break;
            case SANDWICHES:    if(sandwiches.size() > 0){
                                    String s =  sandwiches.get(sandwiches.size() - 1).getDescription() + " removed from deal\n";
                                    sandwiches.remove(sandwiches.size() - 1);
                                    return s;
                                }
                                break;
            default:            return "Nothing added to deal yet!";
        }
        return "Nothing added to deal yet!";
    }

    private int addTreat(Product treat){
        if(amountTreats == 0){
            System.out.println("No treats in this offer.");
            return -1;
        }
        else if(treats.size() < amountTreats){
            treats.add(treat);
            return TREATS;
        }
        else{
            System.out.println(amountTreats + "/" + amountTreats + " already selected.");
            return -1;
        }
    }

    private int addDrink(Product drink){
        if(amountDrinks == 0){
            System.out.println("No drinks in this offer.");
            return -1;
        }
        else if(drinks.size() < amountDrinks){
            drinks.add(drink);
            return DRINKS;
        }
        else{
            System.out.println(amountDrinks + "/" + amountDrinks + " already selected.");
            return -1;
        }
    }

    private int addFruit(Product fruit){
        if(amountFruit == 0){
            System.out.println("No fruit in this offer.");
            return -1;
        }
        else if(fruits.size() < amountFruit){
            fruits.add(fruit);
            return FRUIT;
        }
        else{
            System.out.println(amountFruit + "/" + amountFruit + " already selected.");
            return -1;
        }
    }

    private int addSandwich(Product sandwich){
        if(amountSandwiches == 0){
            System.out.println("No sandwiches in this offer.");
            return -1;
        }
        else if(sandwiches.size() < amountSandwiches){
            sandwiches.add(sandwich);
            return SANDWICHES;
        }
        else{
            System.out.println(amountSandwiches + "/" + amountSandwiches + " already selected.");
            return -1;
        }
    }

    private int addSnack(Product snack){
        if(amountSnacks == 0){
            System.out.println("No snacks in this offer.");
            return -1;
        }
        else if(snacks.size() < amountSnacks){
            snacks.add(snack);
            return SNACKS;
        }
        else{
            System.out.println(amountSnacks + "/" + amountSnacks + " already selected.");
            return -1;
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