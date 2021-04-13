package interceptor;

import product.Deal;

public class DealContextObject extends ContextObject{
    private Deal deal;

    public DealContextObject(Deal deal){
        this.deal=deal;
    }

    public void setDeal(Deal deal){
        this.deal = deal;
    }

    public Deal getDeal() {
        return deal;
    }

    public String getObj(){
        return "Deal Context Object";
    }
    
    //more getters and setters

}
