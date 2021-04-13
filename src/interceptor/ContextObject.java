package interceptor;

import product.Deal;

public class ContextObject {
    private Deal deal;

    public ContextObject(Deal deal){
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
