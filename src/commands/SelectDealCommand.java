package commands;

import java.util.Stack;

import product.Deal;
import undo.DealMenuOriginator;
import vendingMachine.DealMenu;
import vendingMachine.VendingMachine;

public class SelectDealCommand implements Command{

    VendingMachine machine;
    Deal deal;
    Stack mementoStack;
    Stack originatorStack;
    //DealMenu menu = new DealMenu(); 

    public SelectDealCommand(VendingMachine machine, Deal deal, Stack m, Stack o){
        this.deal = deal;
        mementoStack = m;
        originatorStack = o;
    }

    public void execute() {
        if(deal!=null){
			deal.clearDeal();
        }
		originatorStack.clear();
		mementoStack.clear();
		DealMenuOriginator originator = new DealMenuOriginator();
		//deal = (Deal) menu.getChoice(machine.getDeals().toArray());
    }

}
