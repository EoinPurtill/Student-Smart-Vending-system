package commands;

import java.io.IOException;

import io.DAO;
import vendingMachine.VendingMachine;

public class QuitCommand extends Command implements CommandInterface{

    VendingMachine machine;

    public QuitCommand(VendingMachine machine){
        this.machine = machine;
    }

    public void execute(){
        try{
            System.out.println("Returning to home screen\n");
		    DAO.stockToFile("Stock.txt", machine.getStock());
		    DAO.usersToFile("Users.txt", machine.getUsers());
        }
        catch(IOException ex){
            System.out.println("IO Exception caught");
        }
    }
    
}
