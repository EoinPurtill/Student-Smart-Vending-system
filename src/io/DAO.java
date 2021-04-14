package io;

import java.util.*;
import java.io.*;
import product.*;
import users.*;

public class DAO
{
	public DAO()
	{
		super();
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

	public static ArrayList<Deal> dealReader(String fileName) throws IOException
	{
		ArrayList<Deal> list = new ArrayList<Deal>();
		File f = new File("txt/" + fileName); 
		if(f.exists())
		{
			Scanner in = new Scanner(f);
			String[] fileLine;
			while(in.hasNextLine())
			{
				fileLine = in.nextLine().split(",");
				list.add( new Deal( fileLine[0], Integer.parseInt(fileLine[1]), Integer.parseInt(fileLine[2]),
							Integer.parseInt(fileLine[3]), Integer.parseInt(fileLine[4]), Integer.parseInt(fileLine[5]), Double.parseDouble(fileLine[5]) ) );
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
}