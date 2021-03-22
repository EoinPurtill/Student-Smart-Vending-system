package commands;

import java.io.IOException;

import product.Deal;
import product.Order;
import users.User;
import vendingMachine.DealMenu;
import vendingMachine.VendingException;
import vendingMachine.VendingMachine;

public class MultiOrderDealsCommand implements Command {

    VendingMachine machine;
    User user;
    Order order;
    private Double orderValue;
    private int orderComponentType;
    
    public MultiOrderDealsCommand(VendingMachine machine, User user, Order order, Double orderValue){
        this.machine = machine;
        this.user = user;
        this.order = order;
        this.orderValue = orderValue;
    }

    @Override
    public void execute() {
        DealMenu dealMenu = DealMenu.getInstance(user);
        try{
            Deal deal = (Deal)dealMenu.run(machine);
            if(deal!=null){
                try{
                    orderComponentType = order.add(deal);
                    if(orderComponentType < 0){
                        System.out.println("Nothing added to order");
                    }else{
                        System.out.println("Added to order:\n" + deal.getDescription() + ": " + String.format("$%.2f", deal.getPrice()));
                        orderValue += deal.getPrice();
                        System.out.println("Order value: " + String.format("$%.2f", orderValue));
                        
                    }
                } catch(VendingException ex){
                    System.out.println(ex.getMessage());
                }

            }else{
                System.out.println("No deal added.");
            }
        }catch(IOException ex){
            System.out.println("IO Exception Caught");
        }
    }

    public int getOrderComponentType(){
        return orderComponentType;
    }
    public Double getOrderValue(){
        return orderValue;
    }
    
    
    
}
