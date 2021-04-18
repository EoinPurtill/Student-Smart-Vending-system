import java.io.IOException;

import interceptor.*;

import vendingMachine.*;

/**
   This program simulates a vending machine.
*/
public class VendingMachineSimulation
{ 
   static SystemLogger mySystemLogger = new SystemLogger() {
      @Override
      public void onLogEvent(LogContextObject context) {
      }
   };
   static DealLogger myDealLogger = new DealLogger() {
      @Override
      public void onDealSale(DealContextObject context) {
      }
   }; 


   public static void main(String[] args) throws IOException
   { 
      VendingMachine machine = VendingMachine.getInstance();
      VendingMachineMenu menu = VendingMachineMenu.getInstance();
	   String summary = (String)menu.run(machine);
	   System.out.println("\n" + summary);
   }
}
