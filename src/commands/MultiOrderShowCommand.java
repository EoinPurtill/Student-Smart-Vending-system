package commands;

import product.Product;
import vendingMachine.VendingMachine;

public class MultiOrderShowCommand extends Command implements CommandInterface{

    VendingMachine machine;
    
    public MultiOrderShowCommand(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void execute() {
        if(machine.getProductTypes(false).length == 0){
			System.out.println("No Options Currently Available");
        }
		else
		{
			for (Product p : machine.getProductTypes(false)){
			    System.out.println(p);
            }
        }
        
    }
    
}
