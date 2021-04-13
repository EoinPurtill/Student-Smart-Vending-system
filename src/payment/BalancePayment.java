package payment;

public class BalancePayment extends Paymenthandler {
    @Override
    public double makePayment(double balance) {
        // overriding the make payment method
        payment.ProcessPayment("Balance Payment");
        return 0;
    }

}
