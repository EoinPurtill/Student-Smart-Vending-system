package vendingMachine;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.io.IOException;
import java.io.Console;
import users.Operator;
import users.User;
import product.*;
import undo.OrderMenuMemento;
import undo.OrderMenuOriginator;
import undo.MenuMemento;
import undo.MenuOriginator;
import io.*;

public class MultiOrderMenu extends Menu{
    private static MultiOrderMenu instance = new MultiOrderMenu();
    private static Stack mementoStack = new Stack<>(), originatorStack = new Stack<>();
	private static OrderMenuOriginator originator = new OrderMenuOriginator();
    private static User user;

    private MultiOrderMenu(){
		super();
	}

    public static MultiOrderMenu getInstance(User user_){
        user = user_;
		return instance;
	}

	public void setOriginator(OrderMenuOriginator originator_){
		originator = originator_;
	}

	public void undo(Order order){
		if(mementoStack.size() > 0 && originatorStack.size() > 0){
			System.out.println( order.removeItem( ((Integer)((OrderMenuMemento)mementoStack.peek()).getState()).intValue() ) );
			originator = (OrderMenuOriginator)originatorStack.pop();
			originator.restore((MenuMemento)mementoStack.pop());
		}else{
			System.out.println("No items to remove!");
		}
	}

	@SuppressWarnings("unchecked")
	public void setOriginatorState(int state){
		originator.setValue(state);
		mementoStack.push(originator.createMemento());
		originatorStack.push(originator);
		originator = new OrderMenuOriginator();
	}

	public int getOriginatorState(){
		return originator.getValue();
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
										int orderComponentType = order.add(deal);
										if(orderComponentType < 0){
											System.out.println("Nothing added to order");
										}else{
											System.out.println("Added to order:\n" + deal.getDescription() + ": " + String.format("$%.2f", deal.getPrice()));
											orderValue += deal.getPrice();
											System.out.println("Order value: " + String.format("$%.2f", orderValue));
											this.setOriginatorState(orderComponentType);
										}
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
									int orderComponentType = order.add(p);
									if(orderComponentType < 0){
										System.out.println("Nothing added to order");
									}else{
										orderValue += p.getPrice();
										System.out.printf("%s added to order\nOrder value:  $%.2f\n", p, orderValue);
										this.setOriginatorState(orderComponentType);
									}
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

				case "U":		undo(order);
								orderValue = order.getPrice();
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