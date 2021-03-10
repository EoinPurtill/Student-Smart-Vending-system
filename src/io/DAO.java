package io;

import java.util.*;
import java.io.*;
import product.*;
import users.*;
import coin.*;

public class DAO
{
	public DAO()
	{
		super();
	}
	
	public static Coin[] currencyReader(String fileName) throws IOException
	{
		File f = new File("txt/" + fileName); 
		Coin[] coins;
		if(f.exists())
		{
			Scanner in = new Scanner(f);
			Scanner counter = new Scanner(f); int lines = 0;
			while(counter.hasNextLine())
			{
				lines++;
				counter.nextLine();
			}
			coins = new Coin[lines];
			int i = 0;
			String[] fileLine;
			while(in.hasNextLine())
			{
				fileLine = in.nextLine().split(",");
				coins[i] = new Coin(Double.parseDouble(fileLine[1]), fileLine[0]);
				i++;
			}
			counter.close(); in.close();
		}
		else
		{
			coins = new Coin[1];
		}

		return coins;
	}
	
	public static ArrayList<LineItem> stockReader(String fileName) throws IOException
	{
		ArrayList<LineItem> list = new ArrayList<LineItem>();
		File f = new File("txt/" + fileName); 

		ProductFactory pf = new ProductFactory();

		if(f.exists())
		{
			Scanner in = new Scanner(f);
			String[] fileLine;
			while(in.hasNextLine())
			{
				fileLine = in.nextLine().split(",");

				if(fileLine[3].equalsIgnoreCase("SNACK")){
					Product p = pf.getProduct("SNACK", fileLine[0], Double.parseDouble(fileLine[1]));
					list.add(new LineItem(p, Integer.parseInt(fileLine[2]), "SNACK"));
				}
				if(fileLine[3].equalsIgnoreCase("FRUIT")){
					Product p = pf.getProduct("FRUIT", fileLine[0], Double.parseDouble(fileLine[1]));
					list.add(new LineItem(p, Integer.parseInt(fileLine[2]), "FRUIT"));
				}
				if(fileLine[3].equalsIgnoreCase("TREAT")){
					Product p = pf.getProduct("TREAT", fileLine[0], Double.parseDouble(fileLine[1]));
					list.add(new LineItem(p, Integer.parseInt(fileLine[2]), "TREAT"));
				}
				if(fileLine[3].equalsIgnoreCase("DRINK")){
					Product p = pf.getProduct("DRINK", fileLine[0], Double.parseDouble(fileLine[1]));
					list.add(new LineItem(p, Integer.parseInt(fileLine[2]), "DRINK"));
				}
				if(fileLine[3].equalsIgnoreCase("SANDWICH")){
					Product p = pf.getProduct("SANDWICH", fileLine[0], Double.parseDouble(fileLine[1]));
					list.add(new LineItem(p, Integer.parseInt(fileLine[2]), "SANDWICH"));
				}
			}
			
			in.close();
		}
		
		return list;
	}
	
	public static ArrayList<CoinLineItem> coinReader(String fileName) throws IOException
	{
		ArrayList<CoinLineItem> list = new ArrayList<CoinLineItem>();
		File f = new File("txt/" + fileName); 
		if(f.exists())
		{
			Scanner in = new Scanner(f);
			String[] fileLine;
			while(in.hasNextLine())
			{
				fileLine = in.nextLine().split(",");
				list.add(new CoinLineItem(new Coin(Double.parseDouble(fileLine[1]), fileLine[0]), Integer.parseInt(fileLine[2])));
			}
			in.close();
		}

			return list;
	}
	
	public static ArrayList<Operator> operatorReader(String fileName) throws IOException
	{
		ArrayList<Operator> list = new ArrayList<Operator>();
		File f = new File("txt/" + fileName); 
		if(f.exists())
		{
			Scanner in = new Scanner(f);
			String[] fileLine;
			while(in.hasNextLine())
			{
				fileLine = in.nextLine().split(",");
				list.add(new Operator(fileLine[0], fileLine[1]));
			}
			in.close();
		}

		return list;
	}

	public static ArrayList<User> userReader(String fileName) throws IOException
	{
		ArrayList<User> list = new ArrayList<User>();
		File f = new File("txt/" + fileName); 
		if(f.exists())
		{
			Scanner in = new Scanner(f);
			String[] fileLine;
			while(in.hasNextLine())
			{
				fileLine = in.nextLine().split(",");
				list.add(new User( fileLine[0], fileLine[1], Double.parseDouble(fileLine[2]) ));
			}
			in.close();
		}

		return list;
	}
	
	public static void stockToFile(String fileName, ArrayList<LineItem> list) throws IOException 
	{
		FileWriter writer = new FileWriter("txt/" + fileName);
		for (LineItem str : list) 
		{
			writer.write(str.toCSV() + "\n");
		}
		writer.close();
	}

	public static void usersToFile(String fileName, ArrayList<User> list) throws IOException 
	{
		FileWriter writer = new FileWriter("txt/" + fileName);
		for (User user : list) 
		{
			writer.write(user.toCSV() + "\n");
		}
		writer.close();
	}
	
	public static void coinsToFile(String fileName, ArrayList<CoinLineItem> list) throws IOException 
	{
		FileWriter writer = new FileWriter("txt/" + fileName);
		for (CoinLineItem str : list) 
		{
			writer.write(str.toCSV() + "\n");
		}
		writer.close();
	}
}