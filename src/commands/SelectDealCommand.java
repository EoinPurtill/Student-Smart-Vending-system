package commands;

import java.util.Stack;

import product.Deal;
import undo.DealMenuOriginator;

import vendingMachine.VendingMachine;

public class SelectDealCommand extends Command implements CommandInterface{

    VendingMachine machine;
    Deal deal;
    Stack mementoStack;
    Stack originatorStack;
 

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
		
    }

}
