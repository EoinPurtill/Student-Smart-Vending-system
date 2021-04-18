package interceptor;

import product.Deal;

public class DealContextObject extends ContextObject{
    private Deal deal;

    public void setDeal(Deal deal){
        this.deal = deal;
    }
    public Deal getDeal() {
        return deal;
    }
    public String getDescription(){
        return deal.getDescription();
    }
    public int getAmountTreats(){
        return deal.getAmountTreats();
    }
    public int getAmountDrinks(){
        return deal.getAmountDrinks();
    }
    public int getAmountSnacks(){
        return deal.getAmountSnacks();
    }
    public int getAmountFruit(){
        return deal.getAmountFruit();
    }
    public int getAmountSandwiches(){
        return deal.getAmountSandwiches();
    }
    public double getDiscount(){
        return deal.getDiscount();
    }

}
