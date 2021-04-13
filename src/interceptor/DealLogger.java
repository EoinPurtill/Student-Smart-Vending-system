package interceptor;
import product.Deal;

public class DealLogger implements Logger {
    
    public void onDealPurchase(ContextObject co){
        Deal deal = co.getDeal();

        if(deal == null){
            co.setDeal(null);
        }
        else{
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(deal);
        }
    }
    
}
