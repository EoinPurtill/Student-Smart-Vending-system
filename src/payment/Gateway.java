package payment;

public class Gateway implements Payment {  

    @Override  
    public void ProcessPayment(String string) {  
        // TODO Auto-generated method stub  
        System.out.println("Transferring to mock payment gateway " + string);  
    }  
    @Override  
    public void ProcessPayment() {  
        // TODO Auto-generated method stub  
        System.out.println("User will Pay on Delivery");  
    }  
}  

