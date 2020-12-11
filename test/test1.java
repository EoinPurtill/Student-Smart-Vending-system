package test;

import static org.junit.Assert.*;
import org.junit.*;

import product.LineItem;
import product.Product;
import vendingMachine.*;
import java.io.IOException;
import java.util.ArrayList;

public class test1 {

    
    /* @Before
    public static void setUp(){
        //complete before statement
    } */

    @Test
    public void testInsertProduct() throws IOException {
        // Create a Vending Machine
        VendingMachine vm = new VendingMachine();
        // Create test product
        Product testProduct = new Product("String Beans", 2.9);
        // Turn product into a line item
        LineItem lineItem = new LineItem(testProduct, 1);
        // Adding the line item to the vending machine
        vm.addProduct(testProduct, 1);
        ArrayList<LineItem> a = vm.getStock();
        // Validate addition
        System.out.println(vm.getStock());

        // Asserting that it has been added
        assertSame(a.get(0).getProd().getDescription(), lineItem.getProd().getDescription());
    }
}
