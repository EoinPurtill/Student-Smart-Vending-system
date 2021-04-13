package commands;

import java.io.IOException;
import io.*;
import product.*;
import users.*;
import vendingMachine.*;
import vendingMachine.VendingMachine;

public class DealMenuCommand extends Command implements CommandInterface{

    VendingMachine machine;
    User user;
    private String sessionSummary;
    
    public DealMenuCommand(VendingMachine machine, User user){
        this.machine = machine;
        this.user = user;
        this.sessionSummary = "";
    }

    public String getSessionSummary(){
        return sessionSummary;
    }

    public void execute(){

        DealMenu dealMenu = DealMenu.getInstance(user);
        try{
            Deal deal = (Deal)dealMenu.run(machine);
            if(deal!=null){
                try{
                    String dealDescription = deal.getDescription();
                    double dealPrice = deal.getPrice();
                    String msg = machine.buyDeal(deal, user);
                    System.out.println("Purchased: " + msg);
                    deal.clearDeal();
                    DAO.stockToFile("Stock.txt", machine.getStock());
                    DAO.usersToFile("Users.txt", machine.getUsers());
                    sessionSummary += dealDescription + ": $" + String.format("%.2f", dealPrice) + "\n" + "\n";
                }catch(VendingException ex){
                    System.out.println(ex.getMessage());
                }catch(IOException ex){
                    System.out.println("IO Exception Caught");
                }
            } 
        }catch(IOException ex){
            System.out.println("DealMenu IO Exception Caught");
        }  

    }

}
