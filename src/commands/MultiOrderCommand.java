package commands;

import java.io.IOException;
import vendingMachine.*;
import product.*;
import io.*;
import users.*;

public class MultiOrderCommand extends Command implements CommandInterface{
    
    VendingMachine machine;
    User user;
    private String sessionSummary;


    public MultiOrderCommand(VendingMachine machine, User user){

        this.machine = machine;
        this.user = user;

    }

    public String getSessionSummary(){
        return sessionSummary;
    }

    public void execute() {
        MultiOrderMenu multiMenu = MultiOrderMenu.getInstance(user);
        try{
            Order order = (Order)multiMenu.run(machine);
            try{
                String orderSummary = "ORDER:\n" + order.toString() + "\n";
                System.out.println(machine.processOrder(order, user));
                DAO.stockToFile("Stock.txt", machine.getStock());
                DAO.usersToFile("Users.txt", machine.getUsers());
                sessionSummary += orderSummary + "\n" + "\n";
            }catch(VendingException ex){
                System.out.println(ex.getMessage());
            }catch(NullPointerException ex){
                System.out.println("Nothing Added to Order\n");
            }catch(IOException ex){
                System.out.println("IO Exception caught");
            }
        }catch(IOException ex){
            System.out.println("MultiMenu IO Exception caught");
        }
    }
}