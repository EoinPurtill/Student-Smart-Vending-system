package payment;

public class Gateway implements Paymentsystem {  
    @Override  
    public void ProcessPayment(String string) {  
        // TODO Auto-generated method stub  

        System.out.println("Using mock payment gateway for " + string);  
    }  
    @Override  
    public void ProcessPayment() {  
        // TODO Auto-generated method stub  
        System.out.println("User will Pay on Delivery");  
    }  
} 
