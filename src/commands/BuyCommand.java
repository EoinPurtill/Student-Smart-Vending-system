package commands;

import java.io.IOException;

import io.DAO;
import product.Product;
import users.User;
import vendingMachine.VendingException;
import vendingMachine.VendingMachine;
import vendingMachine.VendingMachineMenu;

public class BuyCommand implements Command{
    
    VendingMachine machine;
    VendingMachineMenu menu;
    User user;
    private String sessionSummary;

    public BuyCommand(VendingMachine machine, User user){
        this.machine = machine;
        this.user = user;
    }

    public String getSessionSummary(){
        return sessionSummary;
    }

    public void execute(){
        if(machine.getProductTypes(false).length != 0){
		    try{
			    Product p = (Product) menu.getChoice(machine.getProductTypes(false));
				String output = machine.buyProduct(p, user);
				System.out.println(output);
				DAO.stockToFile("Stock.txt", machine.getStock());
				DAO.usersToFile("Users.txt", machine.getUsers());
				sessionSummary += p.getDescription() + ": $" + String.format("%.2f", p.getPrice()) + "\n";
            }	
			catch(NullPointerException except){
				System.out.println("No Options Currently Available");
			}
			catch (VendingException ex){
				System.out.println(ex.getMessage());
			}
            catch (IOException ex){
                System.out.println("IO Exception caught");
            }
		}
        else{
            System.out.println("No Options Currently Available");
        }



    }
    
}
