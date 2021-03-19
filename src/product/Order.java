package product;

import java.util.ArrayList;

public class Order{
    private ArrayList<Product> singleItems;
    private ArrayList<Deal> deals;

    public Order(){
        singleItems = new ArrayList<Product>();
        deals = new ArrayList<Deal>();
    }

    public double getPrice(){
        double price = 0.0;
        for (Product product : singleItems){
            price += product.getPrice();
        }
        for (Deal deal : deals){
            price += deal.getPrice();
        }
        return price;
    }

    public void addProduct(Product product){
        singleItems.add(product);
    }

    public void addDeal(Deal deal){
        deals.add(deal);
    }

    public ArrayList<Product> getSingleItems(){
        return singleItems;
    }

    public ArrayList<Deal> getDeals(){
        return deals;
    }

    public boolean itemsAdded(){
        return !( singleItems.isEmpty() && deals.isEmpty() );
    }

    public String toString(){
        String returnString = "Items:\n";
        for(Product p : singleItems)
            returnString += p.getDescription() + "\n";
        returnString += "Deals:\n";
        for(Deal d : deals)
            returnString += d.getDescription() + "\n";
        return returnString + "Total Price: $" + String.format("%.2f", this.getPrice());
    }
}