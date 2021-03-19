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

public class DealMenu extends Menu{
    private static DealMenu instance = new DealMenu();
    private static Stack mementoStack = new Stack<>(), originatorStack = new Stack<>();
    private static User user;

    private DealMenu(){
		super();
		
		try{
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

    public static DealMenu getInstance(User user_){
        user = user_;
		return instance;
	}
   
    //TODO: add UNDO functionality to include memento
	@SuppressWarnings("unchecked")
	public Object run(VendingMachine machine) throws IOException, NullPointerException{
		double orderValue = 0.0;
		Deal deal = null;
		boolean more = true;
		while(more){
			System.out.println("D)eals  B)uy  S)elect Deal  A)dd  V)iew Balance  U)ndo  C)ancel");
			String command = in.nextLine().toUpperCase();
			switch(command){
				case "D":	for(Deal d : machine.getDeals())
								System.out.println(d);
							break;

				case "B":	if(deal == null){
								System.out.println("No deal selected!\n");
							} else if(!deal.isComplete()){
								System.out.println("Deal not complete!\n");
							}else{
								Deal returnDeal = new Deal( deal.getDescription(), deal.getAmountTreats(), deal.getAmountDrinks(), deal.getAmountSnacks(), deal.getAmountFruit(), deal.getAmountSandwiches(),
															deal.getDiscount(), (ArrayList<Product>)deal.getTreats().clone(), (ArrayList<Product>)deal.getDrinks().clone(),
															(ArrayList<Product>)deal.getSnacks().clone(), (ArrayList<Product>)deal.getFruits().clone(), (ArrayList<Product>)deal.getSandwiches().clone() );
								deal.clearDeal();
                                mementoStack.clear();
                                originatorStack.clear();
								return returnDeal;
							}
							break;

				case "S":	if(deal!=null)
								deal.clearDeal();
							deal = (Deal) getChoice(machine.getDeals().toArray());
							break;

				case "A":	if(deal == null){
								System.out.println("No deal selected!\n");
							} else {
								try
								{
									Product p = (Product) getChoice(machine.getProductTypes(false));
									if(deal.addItem(p))
										System.out.printf("%s added to order\n", p.getDescription());
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
							break;
				
				case "V":	System.out.printf("Balance:  $%.2f\n", this.user.getCredit());
							break;

				case "C":	System.out.println("Returning to previous menu\n");
							deal.clearDeal();
							deal = null;
							more = false;
                            mementoStack.clear();
                            originatorStack.clear();
							break;

				default:	System.out.println("Invalid input\n\n"); break;
			}
		}
        mementoStack.clear();
        originatorStack.clear();
		return null;
	}
}