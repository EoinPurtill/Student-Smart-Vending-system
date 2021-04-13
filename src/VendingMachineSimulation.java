import java.io.IOException;

import interceptor.*;

import java.io.*;
import vendingMachine.*;

/**
   This program simulates a vending machine.
*/
public class VendingMachineSimulation
{ 
   //implement concrete in
   
   public static void main(String[] args) throws IOException
   { 
      VendingMachine machine = VendingMachine.getInstance();
      VendingMachineMenu menu = VendingMachineMenu.getInstance();
	   String summary = (String)menu.run(machine);
	   System.out.println("\n" + summary);
   }
}
