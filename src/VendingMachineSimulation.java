import java.io.IOException;
import java.io.*;
import javafx.application.Application;
import java.util.Scanner;
import vendingMachine.*;
import gui.*;

public class VendingMachineSimulation{ 
   public static void main(String[] args) throws IOException{
	    
	  VendingMachine machine = VendingMachine.getInstance();
	  VendingMachineMenu menu = VendingMachineMenu.getInstance();
	  
	  Scanner in = new Scanner(System.in); String c = "";
	  while(!(c.equals("~"))){
		System.out.println("Enter 'C' for CLI interface\nEnter 'G' for GUI interface\nEnter '~' to exit");
		c = in.nextLine().toUpperCase();
		if(c.equals("C")){
			menu.run(machine);
			c = "~";
		}	
		else if(c.equals("G")){
			Application.launch(VendingGUI.class);
			c = "~";
		}
		else if(c.equals("~")){
			System.out.println("Quitting...");
		}	
		else
			System.out.println("Invalid Input");
	  }
   }
}
