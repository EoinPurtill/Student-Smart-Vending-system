package vendingMachine;

import java.util.ArrayList;
import java.io.*;
import product.*;
import coin.*;
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
   private ArrayList<CoinLineItem> coins;
   private ArrayList<CoinLineItem> currentCoins;

   private VendingMachine() 
   { 
	   try{
		  stock = DAO.stockReader("Stock.txt");
		  coins = DAO.coinReader("Money.txt");
		  currentCoins = new ArrayList<CoinLineItem>();
		  operators = DAO.operatorReader("Operators.txt");
		  users = DAO.userReader("Users.txt");
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
   
   public String addCoin(Coin money)
   {
	   
	   String output = ""; 
	   boolean addNew = true;
	   for(int i = 0; i < currentCoins.size(); i++)
	   {
		   if(currentCoins.get(i).getCoin().compareTo(money) == 0)
		   {
			   currentCoins.get(i).add();
			   addNew = false;
			   i = currentCoins.size();
		   }
	   }
	   if(addNew)
	   {
			currentCoins.add(new CoinLineItem(money, 1));
	   }
	   double sum = 0;
		for(int i = 0; i < currentCoins.size(); i++)
		{
			sum += currentCoins.get(i).total();
		}
	   output=("Total Credit:  $" + String.format("%1.2f", sum) + "\n");
	   
	   return output;
   }
   
   public String removeMoney(boolean isOperator)
   {
	   if(isOperator)
	   {
		   String ret = "Machine Empty: No Coins to Collect";
		   if(!(coins.isEmpty()))
		   {
			   double sum= 0;
				for(int j = 0; j < coins.size(); j++)
				{
					sum += coins.get(j).total(); coins.get(j).empty();
				}
				if(sum > 0)
				{					
					ret = String.format("$%1.2f", sum) + ": All Coins Collected";
				}
		   }
		   return ret;
	   }
	   else
	   {
		   String ret = "No Money Inserted";
		   if(!(currentCoins.isEmpty()))
		   {
			   double sum = 0;
			   for(int i = 0; i < currentCoins.size(); i++)
			   {
				   sum += currentCoins.get(i).total(); currentCoins.get(i).empty();
			   }
				if(sum > 0)
				{
					ret = String.format("$%1.2f", sum) + " Returned";
				}
				return ret;
			}
			return ret;
	   }
   }
   
   public void transferCoins()
   {
		for(int i = 0; i < currentCoins.size(); i++)
		{
			boolean addNew = true;
			for(int j = 0; j < coins.size(); j++)
			{
				if(currentCoins.get(i).getCoin().compareTo(coins.get(j).getCoin()) == 0)
				{
					coins.get(j).add(currentCoins.get(i).getQuantity());
					currentCoins.get(i).empty();
					addNew = false; j = coins.size();
				}
			}
			if(addNew)
			{
				coins.add(new CoinLineItem(currentCoins.get(i).getCoin(), currentCoins.get(i).getQuantity()));
				currentCoins.get(i).empty();
			}
		}
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

	public boolean processOrder(Order order, User user) throws VendingException{
		ArrayList<Product> itemsList = order.getSingleItems();
		ArrayList<Deal> dealList = order.getDeals();

		if(itemsList.size() + dealList.size() == 0){
			System.out.println("Order is empty");
			return false;
		}

		double totalPrice = 0.0;
		String orderDetails = "ORDER:\n";
		for(Product prod : itemsList){
			try{
				this.buyProduct(prod, user);
				totalPrice += prod.getPrice();
				orderDetails += "-" + prod + "\n";
			}catch(VendingException ex){
				//TODO: implement memento here to restore vending machine state and user balance
				throw new VendingException(ex.getMessage());
			}
		}
		double dealsPrice = 0.0;
		for(Deal deal : dealList){
			//TODO: Stock Removal Logic
			dealsPrice += deal.getPrice();
			totalPrice += dealsPrice;
			orderDetails += "-" + deal + "\n";
		}
		
		if(user.getCredit() < dealsPrice){
			//TODO: implement memento here to restore vending machine state
			throw new VendingException("Not enough credit to complete order");
		}

		System.out.printf("ORDER COMPLETE:\nTotal Price:  $%.2f\nNew Balance:  $%.2f\n\n", totalPrice, user.getCredit());

		return true;
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

   public ArrayList<CoinLineItem> getCoins()
   {
	   return coins;
   }

   	private double getValueInserted(){
		double sum = 0.0;
		for(int i = 0; i < currentCoins.size(); i++)
		{
			sum += currentCoins.get(i).total();
		}
		return sum;
	}
}