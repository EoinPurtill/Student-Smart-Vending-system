import java.io.IOException;
import java.io.*;
import vendingMachine.*;

/**
   This program simulates a vending machine.
*/
public class VendingMachineSimulation
{ 
   public static void main(String[] args) throws IOException
   { 
      VendingMachine machine = VendingMachine.getInstance();
      VendingMachineMenu menu = VendingMachineMenu.getInstance();
	   String summary = (String)menu.run(machine);
	   System.out.println("\n" + summary);
   }
}
