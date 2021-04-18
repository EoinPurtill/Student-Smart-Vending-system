package interceptor;
import product.Deal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DealLogger implements DealSaleInterceptor {
    
    public void onDealSale(DealContextObject co){
        Deal deal = co.getDeal();

        if(deal == null){
            co.setDeal(null);
        }
        else{
            StringBuilder sb = new StringBuilder();
            sb.append("Date and Time of Purchase: " + getTimeAndDate() + "\n");
            sb.append("Deal Description: " + deal.getDescription() +"\n");
            sb.append(deal.getAmountDrinks() + "x Drinks.\n");
            sb.append(deal.getAmountFruit() + "x Fruit.\n");
            sb.append(deal.getAmountSandwiches() + "x Sandwiches.\n");
            sb.append(deal.getAmountSnacks() + "x Snacks.\n");
            sb.append(deal.getAmountTreats() + "x Treats.\n");
            sb.append("Discount: " + deal.getDiscount());
            System.out.println(sb);
        }
    }
    public String getTimeAndDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now);
    }
}
