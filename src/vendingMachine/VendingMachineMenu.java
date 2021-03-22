package vendingMachine;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.Console;
import users.Operator;
import users.User;
import product.*;
import io.*;
import commands.*;

/**   
A menu from the vending machine.
*/
public class VendingMachineMenu extends Menu
{    
	//Creates a private and static single instance of VendingMachineMenu()
	private static VendingMachineMenu instance = new VendingMachineMenu();

	private OperatorMenu opMenu;
	private String sessionSummary;

	private VendingMachineMenu(){
		super();
		
		try{
			opMenu = new OperatorMenu();
			sessionSummary = "";
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static VendingMachineMenu getInstance(){
		return instance;
	}
   
	/**
	Runs the vending machine system.
	@param machine the vending machine
	*/
	public Object run(VendingMachine machine) throws IOException, NullPointerException{
		boolean continueSim = true;
		boolean more = false;

		while(continueSim){
			User user = null;
			System.out.println("Please present student ID card(Enter Student ID Number)");
			System.out.println("Enter ~ to exit");
			String enteredID = in.nextLine().toUpperCase();
			if(enteredID.equals("~")){
				more = false;
				continueSim = false;
			}
			else{
				user = machine.userLogin(enteredID);
				more = (user != null);
				if(!more)
					System.out.println("Card not recognized\n");
			}			
				
			while (more){ 
				System.out.println("S)how Products  M)ulti-order  D)eals  B)uy  V)iew Balance  O)perator Functions  Q)uit");
				String command = in.nextLine().toUpperCase();

				if (command.equals("S"))
				{  
					ShowProductCommand spc = new ShowProductCommand(machine);
					spc.execute();	
				}
				else if (command.equals("M")) //allows user to create order
				{
					MultiOrderCommand moc = new MultiOrderCommand(machine, user);
					moc.execute();
					this.sessionSummary += moc.getSessionSummary();

				}
				else if (command.equals("D")) //allows user to create order from offers
				{ 
					DealMenuCommand dc = new DealMenuCommand(machine, user);
					dc.execute();
					this.sessionSummary += dc.getSessionSummary();
				}
				else if (command.equals("B")) 
				{              
					//BuyCommand bc = new BuyCommand(machine, user);
					//bc.execute();
					//this.sessionSummary += bc.getSessionSummary();

					if(machine.getProductTypes(false).length != 0){
						try{
							Product p = (Product) getChoice(machine.getProductTypes(false));
							String output = machine.buyProduct(p, user);
							System.out.println(output);
							DAO.stockToFile("Stock.txt", machine.getStock());
							DAO.usersToFile("Users.txt", machine.getUsers());
							sessionSummary += p.getDescription() + ": $" + String.format("%.2f", p.getPrice()) + "\n";
						}	
						catch(NullPointerException except){
							System.out.println("No Options Currently Available");
						}
						catch (VendingException ex){
							System.out.println(ex.getMessage());
						}
						catch (IOException ex){
							System.out.println("IO Exception caught");
						}
					}
					else{
						System.out.println("No Options Currently Available");
					}
				}
				else if (command.equals("V"))
				{
					ViewBalanceCommand vbc = new ViewBalanceCommand(user);
					vbc.execute();
				}
				else if (command.equals("O"))
				{  		
					OperatorFunctionsCommand ofc = new OperatorFunctionsCommand(machine);
					ofc.execute();
				}
				else if (command.equals("Q"))
				{
					QuitCommand qc = new QuitCommand(machine);
					qc.execute();
					more = false;			
				}
			}
		}
		if(this.sessionSummary.equals(""))
			return "No Sales This Session";
		return "Sales Summary For This Session:\n\n" + this.sessionSummary;
	}
}
