
package test;
import org.junit.*;

import io.Readr;
import product.LineItem;
import product.Product;
import vendingMachine.VendingMachine;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

public class testBridgeVending {
    @Test
    public void whenBridgePatternInvoked_thenConfigSuccess() {
        // a drinkmachine with drinks in it
        VendingMachine2 DrinkMachine = new VendingMachine2(new DrinkStock());

        assertEquals(DrinkMachine.createMachine(), "Water is god");
    }
}
