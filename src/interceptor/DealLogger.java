package interceptor;
import product.Deal;

public class DealLogger implements DealSaleInterceptor {
    
    public void onDealSale(DealContextObject co){
        Deal deal = co.getDeal();

        if(deal == null){
            co.setDeal(null);
        }
        else{
            System.out.println(deal);
        }
    }
    
}
