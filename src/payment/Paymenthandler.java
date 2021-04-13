package payment;

public abstract class Paymenthandler {

    public Payment payment; 

    public abstract double makePayment(double balance);
}
