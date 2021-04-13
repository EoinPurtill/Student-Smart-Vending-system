package interceptor;
import product.Deal;

public class DealInterceptor implements Interceptor {
    
    public void interceptorExecute(ContextObject co){
        Deal deal = co.getDeal();

        if(deal == null){
            co.setDeal(null);
        }
        else{
            System.out.println(deal);
        }
    }
    
}
