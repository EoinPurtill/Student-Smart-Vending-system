package test;

import org.junit.*;

import payment.BalancePayment;
import payment.Gateway;
import payment.Payment;
import product.Drink;
import users.User;
import static org.junit.Assert.*;
import java.io.IOException;

public class testProcessPayment {
    User user = new User("12312321", "sdfe", 43);
    Drink d = new Drink("Drink", 50);
    Payment testPayment = new BalancePayment();

    @Before
    public void setUp() throws IOException {
        testPayment.payment = new Gateway();

        testPayment.makePayment(d, user);
    }

    @Test
    public void test_confirmPay() {
        assertTrue(testPayment.getConfirmation() == true);
    }

}