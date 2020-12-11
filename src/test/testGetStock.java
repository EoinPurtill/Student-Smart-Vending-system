package test;

import org.junit.*;

import io.Readr;
import product.LineItem;
import product.Product;
import vendingMachine.VendingMachine;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

public class testGetStock {

    private static VendingMachine vm;

    /**
     * Sets up the test fixture. (Called before every test case method.)
     * 
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {
        vm = new VendingMachine();
    } 
    
    @Test
    public void test_getStock() throws IOException {
        // Create stock list as ArrayList from stock.txt
        ArrayList<LineItem> defaultStock = new ArrayList<LineItem>();
        defaultStock = Readr.stockReader("Stock.txt");
        // Now check if the formerly initialized vending machine has the same default stock values 
        assertSame(vm.getStock(), defaultStock);
    }

}