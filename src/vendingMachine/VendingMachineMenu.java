package vendingMachine;

import java.util.Scanner;
import java.io.IOException;
import java.io.Console;
import users.Operator;
import users.User;
import product.*;
import coin.*;
import io.*;

/**   
A menu from the vending machine.
*/
public class VendingMachineMenu extends Menu
{    
	//Creates a private and static single instance of VendingMachineMenu()
	private static VendingMachineMenu instance = new VendingMachineMenu();

	private static Coin[] coins;
	private OperatorMenu opMenu;

	private VendingMachineMenu(){
		super();
		
		try{
			opMenu = new OperatorMenu();
			coins = DAO.currencyReader("Money.txt");
		}catch(IOException ex){
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
	public void run(VendingMachine machine) throws IOException, NullPointerException{
		boolean continueSim = true;
		boolean more = false;

		while(continueSim){
			User user = null;
			System.out.println("Please present student ID card(Enter Student ID Number)");
			System.out.println("Enter ~ to exit");
			String enteredID = in.nextLine().toUpperCase();
			if(enteredID.equals("~")){
				continueSim = false;
			}
			else{
				user = machine.userLogin(enteredID);
				more = (user != null);
				if(!more)
					System.out.println("Card not recognized\n");
			}			
				
			while (more)
			{ 
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
					multiOrderMenu(machine, user);	
				}
				else if (command.equals("D")) //allows user to create order from offers
				{ 
					System.out.println("PLACEHOLDER: TODO: add special offer functionality");
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
							opMenu.run(machine);
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
	}

	//TODO: add UNDO functionality to include memento
	public void multiOrderMenu(VendingMachine machine, User user) throws IOException{
		Order order = new Order();
		double orderValue = 0.0;
		boolean more = true;
		while(more){
			System.out.println("S)how Products  B)uy  D)eals  A)dd  V)iew Balance  C)ancel");
			String command = in.nextLine().toUpperCase();

			switch(command){
				case "S":  		if(machine.getProductTypes(false).length == 0)
									System.out.println("No Options Currently Available");
								else
								{
									for (Product p : machine.getProductTypes(false))
										System.out.println(p);
								}
								break;

				case "B":		try
								{
									more = !( machine.processOrder(order, user) );
									DAO.stockToFile("Stock.txt", machine.getStock());
									DAO.usersToFile("Users.txt", machine.getUsers());
								}
								catch (VendingException ex)
								{
									System.out.println(ex.getMessage());
								}
								break;

				case "D":		System.out.println("PLACEHOLDER: TODO: add special offer functionality");
								break;

				case "A":		try
								{
									Product p = (Product) getChoice(machine.getProductTypes(false));
									order.addProduct(p);
									orderValue += p.getPrice();
									System.out.printf("%s added to order\nOrder value:  $%.2f\n", p, orderValue);
								}
								catch(NullPointerException except)
								{
									System.out.println("No Options Currently Available");
								}
								catch (VendingException ex)
								{
									System.out.println(ex.getMessage());
								}
								break;

				case "V":		System.out.printf("Balance:  $%.2f\n", user.getCredit());
								break;

				case "C":		System.out.println("Order cancelled\n\n");
								more = false;
								break;

				default:	System.out.println("Invalid input\n\n");
			}
		}
	}
}
