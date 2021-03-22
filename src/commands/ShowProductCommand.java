package commands;

import vendingMachine.*;
import product.*;

public class ShowProductCommand implements Command{
    
    VendingMachine machine;

    public ShowProductCommand(VendingMachine machine){

        this.machine = machine;

    }

    public void execute(){
        if(machine.getProductTypes(false).length == 0){
			System.out.println("No Options Currently Available");
        }
		else{	
		    //getProductTypes() returns an array of products that doesn't contain duplicates
		    for(Product p : machine.getProductTypes(false)){
		        System.out.println(p);
            }
        }	
    }
}
