package facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.DAO;
import product.Deal;
import product.LineItem;
import users.Operator;
import users.User;

public class ReaderFacade {

    private ArrayList<LineItem> stock;
    private ArrayList<Operator> operators;
    private ArrayList<User> users;
    private ArrayList<Deal> deals;
   
    public ReaderFacade() throws IOException
    {
        stock       = DAO.stockReader("Stock.txt");
        operators   = DAO.operatorReader("Operators.txt");
        users       = DAO.userReader("Users.txt");
        deals       = DAO.dealReader("Deals.txt");
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