package payment;

import product.Product;
import users.User;
import vendingMachine.VendingException;

public class BalancePayment extends Payment {
    boolean confirmed = false;

    @Override
    public void makePayment(Product prod, User user) {
        // payment object provides independency
        // Makes sure they are a valid user in the database
        // Checks user balances in comparison to price
        payment.ProcessPayment(user, prod);
        user.lowerBalance(prod.getPrice());

        System.out.println(
                "Purchased: " + prod.getDescription() + ".\nNew Balance: $" + String.format("%.2f", user.getCredit()));
        confirmed = true;

    }

    public boolean getConfirmation() {
        return this.confirmed;
    }
}