package vendingMachine;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.Console;
import users.Operator;
import users.User;
import product.*;
import io.*;

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
	
			while (more){ 
				System.out.println("S)how Products  M)ulti-order  D)eals  B)uy  V)iew Balance  O)perator Functions  Q)uit");
				String command = in.nextLine().toUpperCase();

				if (command.equals("S"))
				{  
					if(machine.getProductTypes(false).length == 0)
						System.out.println("No Options Currently Available");
					else
					{	
						/*
						getProductTypes() returns an array of products that doesn't contain duplicates
						*/
						for (Product p : machine.getProductTypes(false))
							System.out.println(p);
					}	
			
				}
				else if (command.equals("M")) //allows user to create order
				{
					MultiOrderMenu multiMenu = MultiOrderMenu.getInstance(user);
					Order order = (Order)multiMenu.run(machine);
					try{
						String orderSummary = "ORDER:\n" + order.toString() + "\n";
						System.out.println(machine.processOrder(order, user));
						DAO.stockToFile("Stock.txt", machine.getStock());
						DAO.usersToFile("Users.txt", machine.getUsers());
						this.sessionSummary += orderSummary + "\n" + "\n";
					}catch(VendingException ex){
						System.out.println(ex.getMessage());
					}catch(NullPointerException ex){
						System.out.println("Nothing Added to Order\n");
					}
				}
				else if (command.equals("D")) //allows user to create order from offers
				{ 
					DealMenu dealMenu = DealMenu.getInstance(user);
					Deal deal = (Deal)dealMenu.run(machine);
					if(deal!=null){
						try{
							String dealDescription = deal.getDescription();
							double dealPrice = deal.getPrice();
							String msg = machine.buyDeal(deal, user);
							System.out.println("Purchased: " + msg);
							deal.clearDeal();
							DAO.stockToFile("Stock.txt", machine.getStock());
							DAO.usersToFile("Users.txt", machine.getUsers());
							this.sessionSummary += dealDescription + ": $" + String.format("%.2f", dealPrice) + "\n" + "\n";
						} catch(VendingException ex){
							System.out.println(ex.getMessage());
						}
					}
				}
				else if (command.equals("B")) 
				{              
					if(machine.getProductTypes(false).length != 0)
					{
						try
						{
							Product p = (Product) getChoice(machine.getProductTypes(false));
							String output = machine.buyProduct(p, user);
							System.out.println(output);
							DAO.stockToFile("Stock.txt", machine.getStock());
							DAO.usersToFile("Users.txt", machine.getUsers());
							this.sessionSummary += p.getDescription() + ": $" + String.format("%.2f", p.getPrice()) + "\n";
						}
						catch(NullPointerException except)
						{
							System.out.println("No Options Currently Available");
						}
						catch (VendingException ex)
						{
							System.out.println(ex.getMessage());
						}
					}
					else
					{
						System.out.println("No Options Currently Available");
					}
				}
				else if (command.equals("V"))
				{
					System.out.printf("Balance:  $%.2f\n", user.getCredit());
				}
				else if (command.equals("O"))
				{  		
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
				else if (command.equals("Q"))
				{
					System.out.println("Returning to home screen\n");
					DAO.stockToFile("Stock.txt", machine.getStock());
					DAO.usersToFile("Users.txt", machine.getUsers());
					more = false;			
				}
			}
		}
		if(this.sessionSummary.equals(""))
			return "No Sales This Session";
		return "Sales Summary For This Session:\n\n" + this.sessionSummary;
	}
}