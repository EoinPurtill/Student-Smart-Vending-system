package io;

import java.util.*;
import java.io.*;
import product.*;
import coin.*;
import io.*;

public class Writr
{ 
	public Writr()
	{
		super();
	}
	public static void stockToFile(String fileName, ArrayList<LineItem> list) throws IOException 
	{
		FileWriter writer = new FileWriter(fileName);
		for (LineItem str : list) 
		{
			writer.write(str.toCSV() + "\n");
		}
		writer.close();
	}	
	
	public static void coinsToFile(String fileName, ArrayList<CoinLineItem> list) throws IOException 
	{
		FileWriter writer = new FileWriter(fileName);
		for (CoinLineItem str : list) 
		{
			writer.write(str.toCSV() + "\n");
		}
		writer.close();
	}
	
	// public static void salesToFile(String fileName, String itemDesc) throws IOException 
	// {
	// 	FileWriter writer = new FileWriter(fileName);
	
	// 	writer.write(itemDesc.toCSV() + "\n");
	
	// 	writer.close();
	// }

}