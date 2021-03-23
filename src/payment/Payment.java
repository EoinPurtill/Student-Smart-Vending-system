package payment;

public abstract class Payment {
    double value;
    boolean confirmed;
    
    public boolean confirmtransaction(){
        return false;
    }
    
}
