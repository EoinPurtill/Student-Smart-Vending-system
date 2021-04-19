package test;

import org.junit.*;
import users.User;
import static org.junit.Assert.*;
import java.io.IOException;

public class testGetStock {

    User user = null;

    @Before
    public void setUp() throws IOException {
        user = new User("12312321", "sdfe", 43);
    }

    @Test
    public void test_getStock() {
        // Create stock list as ArrayList from stock.txt

        // Now check if the formerly initialized vending machine has the same default
        // stock values
        assertTrue(user.getCredit() == 43.0);
    }

}