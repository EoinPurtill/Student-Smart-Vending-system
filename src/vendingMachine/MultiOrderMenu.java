package vendingMachine;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.io.IOException;
import java.io.Console;
import users.Operator;
import users.User;
import product.*;
import io.*;

public class MultiOrderMenu extends Menu{
    private static MultiOrderMenu instance = new MultiOrderMenu();
    private static Stack mementoStack = new Stack<>(), originatorStack = new Stack<>();
    private static User user;

    private MultiOrderMenu(){
		super();
	}

    public static MultiOrderMenu getInstance(User user_){
        user = user_;
		return instance;
	}

    public Object run(VendingMachine machine) throws IOException, NullPointerException{
        Order order = new Order();
		double orderValue = 0.0;
		boolean more = true;
		while(more){
			System.out.println("S)how Products  B)uy  D)eals  A)dd  V)iew Balance  U)ndo  C)ancel");
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

				case "B":		if(order.itemsAdded())
                                    return order;
                                else
                                    System.out.println("Order Empty!");;
								break;

				case "D":		DealMenu dealMenu = DealMenu.getInstance(user);
                                Deal deal = (Deal)dealMenu.run(machine);
								if(deal!=null){
									try{
										System.out.println("Added to order:\n" + deal.getDescription() + ": " + String.format("$%.2f", deal.getPrice()));
										orderValue += deal.getPrice();
										System.out.println("Order value: " + String.format("$%.2f", orderValue));
										order.addDeal(deal);
									} catch(VendingException ex){
										System.out.println(ex.getMessage());
									}
								}else{
									System.out.println("No deal added.");
								}
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
                                return null;

				default:	System.out.println("Invalid input\n\n");
			}
		}
        return null;
    }
}