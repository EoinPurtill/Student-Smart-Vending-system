package commands;

import java.util.ArrayList;
import java.util.Stack;

import product.Deal;
import product.Product;

public class DealBuyCommand extends Command implements CommandInterface{

    Deal deal;
    Stack mementoStack;
    Stack originatorStack;

    public DealBuyCommand(Deal deal, Stack m, Stack o){
        this.deal = deal;
        mementoStack = m;
        originatorStack = o;
    }

    public void execute(){
        if(deal == null){
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
            returnDeal(returnDeal);
        }
    }
    public Deal returnDeal(Deal returnDeal){
        return returnDeal;
    }
}
