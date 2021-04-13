package vendingMachine;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

				CommandFactory cf = new CommandFactory();

				System.out.println("S)how Products  M)ulti-order  D)eals  B)uy  V)iew Balance  O)perator Functions  P)roduct Types  Q)uit");
				String command = in.nextLine().toUpperCase();

				if (command.equals("S"))
				{  
					ShowProductCommand spc = (ShowProductCommand) cf.getCommand("SHOW_PRODUCT", machine);
					spc.execute();	
				}
				else if (command.equals("M")) //allows user to create order
				{
					MultiOrderCommand moc = (MultiOrderCommand) cf.getCommand("MULTI_ORDER", machine, user);
					moc.execute();
					this.sessionSummary += moc.getSessionSummary();

				}
				else if (command.equals("D")) //allows user to create order from offers
				{ 
					DealMenuCommand dc = (DealMenuCommand) cf.getCommand("DEAL_MENU",machine, user);
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
					ViewBalanceCommand vbc = (ViewBalanceCommand) cf.getCommand("VIEW_BALANCE", machine, user);
					vbc.execute();
				}
				else if (command.equals("O"))
				{  		
					//OperatorFunctionsCommand ofc = new OperatorFunctionsCommand(machine);
					//ofc.execute();
					Console con = System.console(); String pass = "";
					System.out.println("Enter Operator ID:"); String id = in.nextLine();
					System.out.println("Enter Password:"); char[] passArray = con.readPassword();
					for(char c : passArray)
						pass += c;
					
					try
					{
						if(machine.login(id, pass))
						{
							String opSummary = (String)opMenu.run(machine);
							System.out.println(opSummary);
						}
						else
						{
							System.out.println("LOGIN FAILED\nReturning to menu...");
						}
					}
					catch(NullPointerException ex)
					{
						System.out.println("LOGIN FAILED\nReturning to menu...");
					}
				}
				else if (command.equals("P"))
				{
					ProductTypeCommand ptc = (ProductTypeCommand) cf.getCommand("PRODUCT_TYPES");
					ptc.execute();
				}
				else if (command.equals("Q"))
				{
					QuitCommand qc = (QuitCommand) cf.getCommand("QUIT", machine);
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
