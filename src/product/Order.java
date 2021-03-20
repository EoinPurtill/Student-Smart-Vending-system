package product;

import java.util.ArrayList;

public class Order{
    private static final int ITEM = 0, DEAL = 1;
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

    public String removeItem(int itemType){
        switch(itemType){
            case ITEM:  if(singleItems.size() > 0){
                            String s =  singleItems.get(singleItems.size() - 1).getDescription() + " removed from order\n";
                            singleItems.remove(singleItems.size() - 1);
                            return s + "Order Price: $" + String.format("%.2f", this.getPrice()) + "\n";
                        }
                        break;
            case DEAL:  if(deals.size() > 0){
                            String s =  deals.get(deals.size() - 1).getDescription() + " removed from order\n";
                            deals.remove(deals.size() - 1);
                            return s + "Order Price: $" + String.format("%.2f", this.getPrice()) + "\n";
                        }
                        break;
            default:    return "Nothing added to order yet!";
        }
        return "Nothing added to order yet!";
    }

    public int add(Object orderItem){
        if(orderItem instanceof Product)
            return addProduct((Product)orderItem);
        if(orderItem instanceof Deal)
            return addDeal((Deal)orderItem);
        return -1;
    }

    private int addProduct(Product product){
        singleItems.add(product);
        return ITEM;
    }

    private int addDeal(Deal deal){
        deals.add(deal);
        return DEAL;
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