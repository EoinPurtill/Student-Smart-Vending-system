package product;

import java.util.ArrayList;

public class Order{
    private ArrayList<Product> singleItems;
    private ArrayList<Deal> deals;

    public Order(){
        singleItems = new ArrayList<Product>();
        deals = new ArrayList<Deal>;
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
}