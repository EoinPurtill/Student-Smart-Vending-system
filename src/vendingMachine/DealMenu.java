package vendingMachine;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.io.IOException;
import java.io.Console;
import users.Operator;
import users.User;
import product.*;
import undo.DealMenuMemento;
import undo.DealMenuOriginator;
import undo.MenuMemento;
import undo.MenuOriginator;
import io.*;

public class DealMenu extends Menu{
    private static DealMenu instance = new DealMenu();
    private static Stack mementoStack = new Stack<>(), originatorStack = new Stack<>();
	private static DealMenuOriginator originator = new DealMenuOriginator();
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

	public void setOriginator(DealMenuOriginator originator_){
		originator = originator_;
	}

	public void undo(Deal deal){
		if(mementoStack.size() > 0 && originatorStack.size() > 0){
			System.out.println( deal.removeItem( ((Integer)((DealMenuMemento)mementoStack.peek()).getState()).intValue() ) );
			originator = (DealMenuOriginator)originatorStack.pop();
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
		originator = new DealMenuOriginator();
	}

	public int getOriginatorState(){
		return originator.getValue();
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
							}else if(!deal.isComplete()){
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
							originatorStack.clear();
							mementoStack.clear();
							originator = new DealMenuOriginator();
							deal = (Deal) getChoice(machine.getDeals().toArray());
							break;

				case "A":	if(deal == null){
								System.out.println("No deal selected!\n");
							} else {
								try
								{
									Product p = (Product) getChoice(machine.getProductTypes(false));
									int itemType = deal.addItem(p);
									if(itemType < 0){
										System.out.println("Nothing added to order");
									}else{
										System.out.printf("%s added to deal\n", p.getDescription());
										this.setOriginatorState(itemType);
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
							}
							break;
				
				case "V":	System.out.printf("Balance:  $%.2f\n", user.getCredit());
							break;

				case "U":	if(deal != null){
								undo(deal);
							}else{
								System.out.println("No deal selected!\n");
							}

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