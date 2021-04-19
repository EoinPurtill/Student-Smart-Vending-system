package vendingMachine;

import java.io.IOException;
import java.io.Console;
import users.User;
import product.*;
import io.*;
import commands.*;
import interceptor.Dispatcher;
import interceptor.LogContextObject;
import interceptor.SystemLogger;

/**
 * A menu from the vending machine.
 */
public class VendingMachineMenu extends Menu {
	// Creates a private and static single instance of VendingMachineMenu()
	private static VendingMachineMenu instance = new VendingMachineMenu();

	private OperatorMenu opMenu;
	private String sessionSummary;
	
	private VendingMachineMenu(){
		super();

		try {
			opMenu = new OperatorMenu();
			sessionSummary = "";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static VendingMachineMenu getInstance(){
		return instance;
	}

	LogContextObject loc = new LogContextObject();	
	SystemLogger sysLog = new SystemLogger();
	Dispatcher d = new Dispatcher();
	CommandFactory cf = new CommandFactory();
   
	/**
	 * Runs the vending machine system.
	 * 
	 * @param machine the vending machine
	 */
	public Object run(VendingMachine machine) throws IOException, NullPointerException {
		boolean continueSim = true;
		boolean more = false;
		d.register(sysLog);
		
		while(continueSim){
			User user = null;

			loc.setMessage("Please present student ID card(Enter Student ID Number)\nEnter ~ to exit");
			
			d.dispatchSystemLog(loc);

			String enteredID = in.nextLine().toUpperCase();
			if (enteredID.equals("~")) {
				more = false;
				continueSim = false;
			} else {
				user = machine.userLogin(enteredID);
				more = (user != null);
				if(!more){
					loc.setMessage("Card not recognized\n");
					d.dispatchSystemLog(loc);
				}
			}			
				
			while (more){ 

				loc.setMessage("S)how_Products  M)ulti-order  D)eals  B)uy  V)iew_Balance  O)perator_Functions  P)roduct_Types  Q)uit");
				d.dispatchSystemLog(loc);
				String command = in.nextLine().toUpperCase();

				if (command.equals("S"))
				{  
					ShowProductCommand spc = (ShowProductCommand) cf.getCommand("SHOW_PRODUCT", machine);
					spc.execute();	
				}
				else if (command.equals("M")) //allows user to create order
				{
					MultiOrderCommand moc = (MultiOrderCommand) cf.getCommand("MULTI_ORDER", machine, user);
					moc.execute();
					this.sessionSummary += moc.getSessionSummary();

				}
				else if (command.equals("D")) //allows user to create order from offers
				{ 
					DealMenuCommand dc = (DealMenuCommand) cf.getCommand("DEAL_MENU",machine, user);
					dc.execute();
					this.sessionSummary += dc.getSessionSummary();
				}
				else if (command.equals("B")) 
				{              
					if(machine.getProductTypes(false).length != 0){
						try{
							Product p = (Product) getChoice(machine.getProductTypes(false));
							String output = machine.buyProduct(p, user);
							loc.setMessage(output);
							d.dispatchSystemLog(loc);
							DAO.stockToFile("Stock.txt", machine.getStock());
							DAO.usersToFile("Users.txt", machine.getUsers());
							sessionSummary += p.getDescription() + ": $" + String.format("%.2f", p.getPrice()) + "\n";
						}	
						catch(NullPointerException except){
							loc.setMessage("No Options Currently Available");
							d.dispatchSystemLog(loc);
							
						}
						catch (VendingException ex){
							loc.setMessage(ex.getMessage());
							d.dispatchSystemLog(loc);
						}
						catch (IOException ex){
							loc.setMessage("IO Exception caught");
							d.dispatchSystemLog(loc);
							
						}
					}
					else{
						loc.setMessage("No Options Currently Available");
						d.dispatchSystemLog(loc);
					}
				}
				else if (command.equals("V"))
				{

					ViewBalanceCommand vbc = (ViewBalanceCommand) cf.getCommand("VIEW_BALANCE", machine, user);
					
					if(vbc != null){
						vbc.execute();
					}

				}
				else if (command.equals("O"))
				{  		

					Console con = System.console(); String pass = "";
					loc.setMessage("Enter Operator ID:");
					d.dispatchSystemLog(loc);
					String id = in.nextLine();

					loc.setMessage("Enter Password:");
					d.dispatchSystemLog(loc);
					char[] passArray = con.readPassword();

					for(char c : passArray)
						pass += c;
					
					try
					{
						if(machine.login(id, pass))
						{
							String opSummary = (String)opMenu.run(machine);
							loc.setMessage(opSummary);
							d.dispatchSystemLog(loc);
						}
						else
						{
							loc.setMessage("LOGIN FAILED\nReturning to menu...");
							d.dispatchSystemLog(loc);
						}
					}
					catch(NullPointerException ex)
					{
						loc.setMessage("LOGIN FAILED\nReturning to menu...");
						d.dispatchSystemLog(loc);
					}
				}
				else if (command.equals("Q"))
				{
					QuitCommand qc = (QuitCommand) cf.getCommand("QUIT", machine);
					qc.execute();
					more = false;			
				}
			}
		}
		if (this.sessionSummary.equals(""))
			return "No Sales This Session";
		return "Sales Summary For This Session:\n\n" + this.sessionSummary;
	}
}