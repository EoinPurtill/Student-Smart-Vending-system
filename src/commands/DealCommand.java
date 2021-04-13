package commands;

import product.Deal;
import vendingMachine.VendingMachine;

public class DealCommand extends Command implements CommandInterface{

    VendingMachine machine;

    public DealCommand(VendingMachine machine){
        this.machine = machine;
    }

    public void execute(){
        for(Deal d : machine.getDeals()){
			System.out.println(d);
        }
    }
    
}
