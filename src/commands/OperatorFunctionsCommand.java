package commands;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

import vendingMachine.OperatorMenu;
import vendingMachine.VendingMachine;

public class OperatorFunctionsCommand extends Command implements CommandInterface{

    VendingMachine machine;
    private OperatorMenu opMenu;
    private Scanner in = new Scanner(System.in);

    public OperatorFunctionsCommand(VendingMachine machine){
        this.machine = machine;
    }


    public void execute(){

        Console con = System.console(); 
        String pass = "";
        System.out.println("Enter Operator ID:"); 
        String id = in.nextLine();
        System.out.println("Enter Password:"); 
        char[] passArray = con.readPassword();
        for(char c : passArray)
            pass += c;
              
        try
        {
            if(machine.login(id, pass))
            {
                String opSummary = (String) opMenu.run(machine);
                System.out.println(opSummary);
            }
            else
            {
                System.out.println("LOGIN FAILED\nReturning to menu...");
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("!!!!!!!!22222222");
            System.out.println("LOGIN FAILED\nReturning to menu...");
        }
        catch(IOException ex){
            System.out.println("IO Exception caught");
        }

    }
    
}
