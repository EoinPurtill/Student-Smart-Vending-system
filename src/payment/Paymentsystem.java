package payment;

import product.Product;
import users.User;

//Interface  
public interface Paymentsystem {
    // Singular method to be overridden
    void ProcessPayment(User user, Product prod);
}
