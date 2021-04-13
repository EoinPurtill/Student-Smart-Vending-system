package vendingMachine;

import java.util.ArrayList;
import java.util.Stack;

import commands.DealCommand;
import commands.ViewBalanceCommand;

import interceptor.LogContextObject;
import interceptor.SystemLogger;

import java.io.IOException;


import users.User;
import product.*;
import undo.DealMenuMemento;
import undo.DealMenuOriginator;
import undo.MenuMemento;


public class DealMenu extends Menu{
    private static DealMenu instance = new DealMenu();
    private static Stack mementoStack = new Stack<>(), originatorStack = new Stack<>();
	private static DealMenuOriginator originator = new DealMenuOriginator();
    private static User user;

	static LogContextObject loc = new LogContextObject();
	static SystemLogger sysLog = new SystemLogger();

    private DealMenu(){
		super();
	}

    public static DealMenu getInstance(User user_){
        user = user_;
		return instance;
	}

	public static void setOriginator(DealMenuOriginator originator_){
		originator = originator_;
	}

	public static void undo(Deal deal){
		if(!mementoStack.isEmpty() && !originatorStack.isEmpty()){
			loc.setMessage(deal.removeItem( ((Integer)((DealMenuMemento)mementoStack.peek()).getState()).intValue() ));
			sysLog.onLogEvent(loc);
			originator = (DealMenuOriginator)originatorStack.pop();
			originator.restore((MenuMemento)mementoStack.pop());
		}else{
			loc.setMessage("No items to remove!");
			sysLog.onLogEvent(loc);
		}
	}

	@SuppressWarnings("unchecked")
	public static void setOriginatorState(int state){
		originator.setValue(state);
		mementoStack.push(originator.createMemento());
		originatorStack.push(originator);
		originator = new DealMenuOriginator();
	}

	public int getOriginatorState(){
		return originator.getValue();
	}
   

	@SuppressWarnings("unchecked")
	public Object run(VendingMachine machine) throws IOException, NullPointerException{

		Deal deal = null;
		boolean more = true;
		String noDeal = "no deal selected\n";

		while(more){
			loc.setMessage("D)eals  B)uy  S)elect_Deal  A)dd  V)iew_Balance  U)ndo  C)ancel");
			sysLog.onLogEvent(loc);

			String command = in.nextLine().toUpperCase();
			switch(command){
				case "D":	DealCommand dc = new DealCommand(machine);
							dc.execute();
							break;

				case "B":	if(deal == null){
								loc.setMessage(noDeal);
								sysLog.onLogEvent(loc);

							}else if(!deal.isComplete()){
								loc.setMessage("Deal not complete!\n");
								sysLog.onLogEvent(loc);
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

				case "S":	if(deal!=null){
								deal.clearDeal();
							}
							originatorStack.clear();
							mementoStack.clear();
							originator = new DealMenuOriginator();
							deal = (Deal) getChoice(machine.getDeals().toArray());
							break;

				case "A":	if(deal == null){
								loc.setMessage(noDeal);
								sysLog.onLogEvent(loc);
							} else {
								try
								{
									Product p = (Product) getChoice(machine.getProductTypes(false));
									int itemType = deal.addItem(p);
									if(itemType < 0){
										loc.setMessage("Nothing added to order");
										sysLog.onLogEvent(loc);
									}else{
										
										System.out.printf("%s added to deal\n", p.getDescription());
										this.setOriginatorState(itemType);
									}
								}
								catch(NullPointerException except)
								{
									loc.setMessage("No Options Currently Available");
									sysLog.onLogEvent(loc);
								}
								catch (VendingException ex)
								{
									loc.setMessage(ex.getMessage());
									sysLog.onLogEvent(loc);
								}
							}
							break;
				
				case "V":	ViewBalanceCommand vbc = new ViewBalanceCommand(user);
							vbc.execute();
							break;

				case "U":	if(deal != null){
								undo(deal);
							}else{
								loc.setMessage(noDeal);
								sysLog.onLogEvent(loc);
							}
							break;
				case "C":	loc.setMessage("Returning to previous menu\n");
							sysLog.onLogEvent(loc);
							if(deal != null)
								deal.clearDeal();
							deal = null;
							more = false;
							break;
							
				default:	loc.setMessage("Invalid input\n\n"); 
							sysLog.onLogEvent(loc);
							break;
			}
		}
        mementoStack.clear();
        originatorStack.clear();
		return null;
	}
}