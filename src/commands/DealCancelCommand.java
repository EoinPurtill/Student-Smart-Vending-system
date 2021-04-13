package commands;

import java.util.Stack;

import product.Deal;

public class DealCancelCommand extends Command implements CommandInterface{

    Deal deal;
    Stack mementoStack;
    Stack originatorStack;

    public DealCancelCommand(Deal deal, Stack m, Stack o){
        this.deal = deal;
        mementoStack = m;
        originatorStack = o;
    }


    @Override
    public void execute() {
        System.out.println("Returning to previous menu\n");
	    if(deal!= null){
            deal.clearDeal();
        }
		deal = null;
		
        mementoStack.clear();
        originatorStack.clear();
        
    }
    
}
