package vendingMachine;

import java.util.Scanner;
import java.io.IOException;
import io.*;
import product.*;

public class OperatorMenu extends Menu
{
	private String restockSummary;
	private String newItemSummary;

	public OperatorMenu()
	{
		super();
		this.restockSummary = "";
		this.newItemSummary = "";
	}
	
	public Object run(VendingMachine machine) throws IOException
	{
		boolean more = true;
		while(more)	
		{	
			System.out.println("R)estock  A)dd New Stock  E)xit");
			String option = in.nextLine().toUpperCase();
			if (option.equals("R")) 
			{ 
				if(machine.getProductTypes(true).length != 0)
				{
					try
					{
						System.out.println("Choose Product To Add:");
						Product p = (Product) getChoice(machine.getProductTypes(true));
						System.out.println("Quantity:");
						String command = in.nextLine();
						if(Validator.verifyInt(command))
						{
							int q = Integer.parseInt(command);
							if(!(q > 0))
							{
								System.out.println("\nInvalid Quantity");
							}
							else
							{
								System.out.println(machine.addProduct(p, q));
								System.out.println(p + " : " + q + " Added");
								this.restockSummary += p.getDescription() + ": +" + q + "\n";
							}
						}
						else
						{
							System.out.println("\nInvalid Quantity");
						}
					}
					catch(NullPointerException ex)
					{
						System.out.println("No Options Currently Available");
					}
				}
				else
				{
					System.out.println("No Options Currently Available");
				}
			}
			else if (option.equals("A"))
			{      
				System.out.println("Description:");
				String description = in.nextLine();
				System.out.println("Price:");
				String priceStr = in.nextLine();
				System.out.println("Quantity:");
				String quantityStr = in.nextLine();
				if(Validator.verifyDouble(priceStr) && Validator.verifyInt(quantityStr)){
					double price = Double.parseDouble(priceStr); int quantity = Integer.parseInt(quantityStr);
					if(price > 0 && quantity > 0){
						if(!(machine.containsProduct(price, description))){
							Product prod = new Product(description, price);
							System.out.println(machine.addProduct(prod, quantity));
							this.newItemSummary += prod.getDescription() + ": +" + quantity + "\n";
						}
						else{
							System.out.println("Product Already In Vending Machine.\nPlease Select \"R)estock\" Option"); 
						}
					}else{
						System.out.println("Invalid Input");
					}
				}
				else
				{
					System.out.println("Invalid Input");
				}
			}        
			else if (option.equals("E"))
			{      
				more = false;
			}        
		}
		String fullSummary = "";
		if(!this.restockSummary.equals(""))
			fullSummary += "Items Restocked:\n" + this.restockSummary + "\n";
		if(!this.newItemSummary.equals(""))
			fullSummary += "New Items:\n" + this.newItemSummary + "\n";
		return fullSummary;
	}
}

	