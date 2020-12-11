package test;

import org.junit.*;

import io.Readr;
import product.LineItem;
import product.Product;
import vendingMachine.VendingMachine;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

public class testAddProduct {

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
    public void test_AddProduct() {
        System.out.println(vm.getStock());
        // Create test product
        Product testProduct = new Product("String Beans", 2.9);
        // Turn product into a line item
        LineItem testLineItem = new LineItem(testProduct, 1, "");
        // Adding the line item to the vending machine
        vm.addProduct(testProduct, 1);
        ArrayList<LineItem> a = vm.getStock();

        // Automated test
        assertSame(a.get(0).getProd().getDescription(), testLineItem.getProd().getDescription());
    }

}