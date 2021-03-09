import org.junit.*;

import product.LineItem;
import product.Product;
import vendingMachine.DrinkMachine;
import vendingMachine.DrinkStock;
import vendingMachine.Stockinterface;
import vendingMachine.VendingMachine;
import vendingMachine.VendingMachine2;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

public class testBridgeVending {
    @Test
    public void whenBridgePatternInvoked_thenConfigSuccess() {
        // a drinkmachine with drinks in it
        DrinkMachine drinkmachine = new DrinkMachine(null){
            
        };

        assertEquals(DrinkMachine.createMachine(), "Stock");
    }
}
