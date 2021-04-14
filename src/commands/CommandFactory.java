package commands;

import interceptor.LogContextObject;
import interceptor.SystemLogger;
import users.User;
import vendingMachine.VendingMachine;

public class CommandFactory {

    LogContextObject loc = new LogContextObject();	
	SystemLogger sysLog = new SystemLogger();
    	
   //use getCommand method to get object of type Command 
    public Command getCommand(String commandType, VendingMachine machine, User user){
        if(commandType == null){
            return null;

        } else if(commandType.equalsIgnoreCase("MULTI_ORDER")){
            return new MultiOrderCommand(machine, user);
            
        } else if(commandType.equalsIgnoreCase("DEAL_MENU")){
            return new DealMenuCommand(machine, user);

        }else {
            loc.setMessage("Command type: " + commandType + " is inappropriate for this argument list" );
            sysLog.onLogEvent(loc);
            return null;
        }

        
    }

    public Command getCommand(String commandType, VendingMachine machine){
        if(commandType == null){
            return null;

        } else if(commandType.equalsIgnoreCase("SHOW_PRODUCT")){
            return new ShowProductCommand(machine); 

        } else if(commandType.equalsIgnoreCase("QUIT")){
            return new QuitCommand(machine);
        } 
        else {
            loc.setMessage("Command type: " + commandType + " is inappropriate for this argument list" );
            sysLog.onLogEvent(loc);
            return null;
        }

    }

    public Command getCommand(String commandType, User user){
        if(commandType == null){
            return null;

        } else if(commandType.equalsIgnoreCase("VIEW_BALANCE")){
            return new ViewBalanceCommand(user);
        }else {
            loc.setMessage("Command type: " + commandType + " is inappropriate for this argument list" );
            sysLog.onLogEvent(loc);
            return null;
        }
    }

}