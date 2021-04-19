package payment;

import product.Product;
import users.User;
import vendingMachine.VendingException;

public class Gateway implements Paymentsystem {
    boolean reached = false;
    @Override
    public void ProcessPayment(User user, Product prod) {
        if (user != null && prod.getPrice() <= user.getCredit()) {
            reached = true;
        }
        if (reached)
            System.out.println("Using mock payment gateway for ");
        else {
            throw new VendingException("Not enough credit\n");
        }
    }

}
