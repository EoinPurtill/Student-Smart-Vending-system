package commands;

import java.util.HashMap;
import java.util.Map;

import users.User;
import vendingMachine.VendingMachine;

public class CommandFactory {
    	
   //use getCommand method to get object of type Command 
    public Command getCommand(String commandType, VendingMachine machine, User user){
        if(commandType == null){
            return null;

        } else if(commandType.equalsIgnoreCase("MULTI_ORDER")){
            return new MultiOrderCommand(machine, user);
            
        } else if(commandType.equalsIgnoreCase("DEAL_MENU")){
            return new DealMenuCommand(machine, user);

        } 

        return null;
    }

    public Command getCommand(String commandType, VendingMachine machine){
        if(commandType == null){
            return null;

        } else if(commandType.equalsIgnoreCase("SHOW_PRODUCT")){
            return new ShowProductCommand(machine); 

        } else if(commandType.equalsIgnoreCase("QUIT")){
            return new QuitCommand(machine);
        } 

        return null;

    }

    public Command getCommand(String commandType, User user){
        if(commandType == null){
            return null;

        } else if(commandType.equalsIgnoreCase("VIEW_BALANCE")){
            return new ViewBalanceCommand(user);
        }

        return null;
    }

    public Command getCommand(String commandType){
        if(commandType == null){
            return null;
        
        } else if(commandType.equalsIgnoreCase("PRODUCT_TYPES")){
            return new ProductTypeCommand();
        }

        return null;
    }

}