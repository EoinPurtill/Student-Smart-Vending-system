package payment;

public class Gateway extends Payment {

    Paymenthandler handler;

    public Gateway(boolean confirmed) {
        handler.confirmed = confirmed;
    }

    public double getValue() {
        return super.value;
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
