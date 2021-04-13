package payment;

import product.Product;
import users.User;

public class BalancePayment extends Payment {
    @Override
    public void makePayment(Product prod, User user) {
        // payment object provides independency

        payment.ProcessPayment("Balance Payment");
        user.lowerBalance(prod.getPrice());
        System.out.println(
                "Purchased: " + prod.getDescription() + ".\nNew Balance: $" + String.format("%.2f", user.getCredit()));

    }
}