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
    User user = new User("12312321", "sdfe", 100);
    Drink d = new Drink("Drink", 50);
    Payment testPayment = new BalancePayment();

    @Before
    public void setUp() throws IOException {
        testPayment.payment = new Gateway();
        testPayment.makePayment(d, user);

    }
    @Test
    public void test_confirmPay() {
        System.out.println("Checking if the makePayment() method was called");

        assertTrue(testPayment.getConfirmation() == true);
    }
    @Test
    public void test_userRecieved() {
        System.out.println("Checking if the correct user was received");

        assertSame(user.getID(), "12312321");
    }
    @Test
    public void test_creditSame() {
        System.out.println("Checking if the balance has been decreased");
        assertEquals(user.getCredit(), 50.0, 0);

    }

}