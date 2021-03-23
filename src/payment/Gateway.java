package payment;

public class Gateway extends Payment {

    Paymenthandler handler;

    public Gateway(boolean confirmed) {
        handler.confirmed = confirmed;
    }

    public double getValue() {
        return super.value;
    }

    public void paymentvalidator(){
        
    }

    @Override
    public boolean confirmtransaction() {
        try {
            if (handler.confirmed == true) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

}

/* 
public class Gateway implements Payment {

    Paymenthandler handler;

    public Gateway(boolean confirmed) {
        handler.confirmed = confirmed;
    }

    public double getValue() {
        return super.value;
    }

    public void paymentvalidator(){
        
    }

    @Override
    public boolean confirmtransaction() {
        try {
            if (handler.confirmed == true) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

} */
