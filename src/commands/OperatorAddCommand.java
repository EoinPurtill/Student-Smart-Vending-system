package commands;

import java.util.Scanner;

import io.Validator;
import product.Product;
import vendingMachine.VendingMachine;

public class OperatorAddCommand implements Command{

    VendingMachine machine;
    private String newItemSummary;

    public OperatorAddCommand(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Description:");
        String description = in.nextLine();
        System.out.println("Price:");
        String priceStr = in.nextLine();
        System.out.println("Quantity:");
        String quantityStr = in.nextLine();
        if(Validator.verifyDouble(priceStr) && Validator.verifyInt(quantityStr)){
            double price = Double.parseDouble(priceStr); int quantity = Integer.parseInt(quantityStr);
            if(price > 0 && quantity > 0){
                if(!(machine.containsProduct(price, description))){
                    Product prod = new Product(description, price);
                    System.out.println(machine.addProduct(prod, quantity));
                    newItemSummary = prod.getDescription() + ": +" + quantity + "\n";
                }
                else{
                    System.out.println("Product Already In Vending Machine.\nPlease Select \"R)estock\" Option"); 
                }
            }else{
                System.out.println("Invalid Input");
            }
        }
        else
        {
            System.out.println("Invalid Input");
        }
        in.close();
        
    }
    
    public String getNewItemSummary(){
        return newItemSummary;
    }
    
}
