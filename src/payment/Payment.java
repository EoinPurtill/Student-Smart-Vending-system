package payment;

import product.Product;
import users.User;

public abstract class Payment {

    public Paymentsystem payment; // instance

    public abstract void makePayment(Product prod, User user); // method responsible to makePayment

}
