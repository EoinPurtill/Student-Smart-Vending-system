package vendingMachine;

import java.util.ArrayList;
import java.io.*;
import product.*;
import users.Operator;
import users.User;
import io.*;


public class VendingMachine 
{  

   //creates a private and static single instance of vendingMachine.
   private static VendingMachine instance = new VendingMachine();
   
   private ArrayList<LineItem> stock;
   private ArrayList<Operator> operators;
   private ArrayList<User> users;
   private ArrayList<Deal> deals;

   private VendingMachine() 
   { 
	   try{
		  stock = DAO.stockReader("Stock.txt");
		  operators = DAO.operatorReader("Operators.txt");
		  users = DAO.userReader("Users.txt");
		  deals = DAO.dealReader("Deals.txt");
	   }catch (IOException ex) {
		   ex.printStackTrace();

	   }
   }
   
   //Returns the only available Vending Machine object.
   public static VendingMachine getInstance(){
      return instance;
   }
   
   public Product[] getProductTypes(boolean isOperator)
   {
	   ArrayList<Product> temp = new ArrayList<Product>();
	  
		for(int i = 0; i < this.stock.size(); i++)
		{
			if(!temp.contains(this.stock.get(i).getProd()))
			{	
				if((this.stock.get(i).getQuantity() > 0) || isOperator)
					temp.add(this.stock.get(i).getProd());
			}
		}
	   
	   Product[] ret = new Product[temp.size()];
	   ret = temp.toArray(ret);
	   return ret;
   }
   
   public String buyProduct(Product prod, User user) throws VendingException
   {
		String output = "";
		if(prod.getPrice() <= user.getCredit())
		{
			for(int j = 0; j < stock.size(); j++)
			{
				if((stock.get(j).getProd().compareTo(prod)) == 0)
				{
					stock.get(j).remove();
					j = stock.size();
				}
			}
			user.lowerBalance(prod.getPrice());
			output = "Purchased: " + prod.getDescription() + ".\nNew Balance: $" + String.format("%.2f", user.getCredit());
		}
		else
		{
			throw new VendingException("Not enough credit!\n");
		}
		return output;
	}
   
	public String buyDeal(Deal deal, User user) throws VendingException
   {
		if( !deal.isComplete() ){
			throw new VendingException("Deal is not complete!\n");
		}
		double price = deal.getPrice();
		if( user.getCredit() >= price ){
			for(Product treat : deal.getTreats())
				buyProduct(treat, user);
			for(Product drink : deal.getDrinks())
				buyProduct(drink, user);
			for(Product fruit : deal.getFruits())
				buyProduct(fruit, user);
			for(Product sandwich : deal.getSandwiches())
				buyProduct(sandwich, user);

			user.increaseBalance( ( deal.getPrice() / (100 - deal.getDiscount()) ) * deal.getDiscount() );
			deal.clearDeal();
			return deal.getDescription() + " Total Price:  " + String.format("$%.2f", price) + "\nNew Balance:  $" + String.format("%.2f", user.getCredit());
		}
		else
		{
			throw new VendingException("Not enough credit!\n");
		}
	}

	public String processOrder(Order order, User user) throws VendingException{
		ArrayList<Product> itemsList = order.getSingleItems();
		ArrayList<Deal> dealList = order.getDeals();

		if(itemsList.size() + dealList.size() == 0){
			throw new VendingException("Order is empty");
		}

		double totalPrice = 0.0;
		for(Product prod : itemsList){
				totalPrice += prod.getPrice();
		}
		double dealsPrice = 0.0;
		for(Deal deal : dealList){
			dealsPrice += deal.getPrice();
		}
		totalPrice += dealsPrice;
		
		if(user.getCredit() < totalPrice){
			throw new VendingException("Not enough credit to complete order");
		}

		String orderDetails = "ORDER:\n";
		for(Product prod : itemsList){
			try{
				this.buyProduct(prod, user);
				orderDetails += "-" + prod + "\n";		
			}catch(VendingException ex){
				//TODO: implement memento here to restore vending machine state and user balance
				throw new VendingException(ex.getMessage());
			}
		}
		for(Deal deal : dealList){
			try{
				this.buyDeal(deal, user);
				orderDetails += "-" + deal + "\n";
			}catch(VendingException ex){
				//TODO: implement memento here to restore vending machine state and user balance
				throw new VendingException(ex.getMessage());
			}
		}

		return "Price:  " + String.format("$%.2f", totalPrice) + "\nNew Balance:  " + String.format("$%.2f", user.getCredit()) + "\n";
	}
   
   public String addProduct(Product prod, int quant)
	{   
	   String output = ""; //I added this guy to this method to collect our output 
							//and return it, it helps with the GUI, i also changed
							//the statement where this is called from OperatorMenu to
							//a println statement so that the needed info is still printed!
	   
	   boolean go = true; int i = 0;
	   while(go && i < stock.size())
	   {
		   if(stock.get(i).compareProducts(prod) == 0)
		   {
			   if(stock.get(i).add(quant))
				   output = "Successfully added"; 
			   else
					output = "Add Unsuccessful"; 
			   go = false;
		   }
		   i++;
	   }
	   if(go)
	   {   
			stock.add(new LineItem(prod, quant, "")); 
			output = "Successfully added"; 
	   }
	   return output;
	}

   public boolean containsProduct(Product p)
   {
	   for(int i = 0; i < stock.size(); i++)
	   {
		   if(stock.get(i).compareProducts(p) == 0)
			   return true;
	   }
	   return false;
   }
   
   public boolean containsProduct(double price, String desc)
   {
	   for(int i = 0; i < stock.size(); i++)
	   {
		    if(stock.get(i).compareProducts(price, desc) == 0)
			   return true;
	   }
	   return false;
   }
   
   public boolean login(String id, String pass) throws NullPointerException
   {
		for(int i = 0; i < operators.size(); i++)
		{
			if(operators.get(i).assertDetails(id, pass))
			{
				return true;
			}
		}
		return false;
   }

   public User userLogin(String id) throws NullPointerException
   {
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).assertDetails(id))
			{
				return users.get(i);
			}
		}
		return null;
   }

   public void trackSales(String prodDesc){
	   

   }
   
   public ArrayList<LineItem> getStock()
   {
	   return stock;
   }
   
   public ArrayList<Operator> getOperators()
   {
	   return operators;
   }
   
   public ArrayList<User> getUsers()
   {
	   return users;
   }

   public ArrayList<Deal> getDeals()
   {
	   return deals;
   }
}