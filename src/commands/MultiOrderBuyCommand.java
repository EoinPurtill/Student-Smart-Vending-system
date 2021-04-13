package commands;

import product.Order;

public class MultiOrderBuyCommand extends Command implements CommandInterface{

    Order order;

    public MultiOrderBuyCommand(Order order){
        this.order = order;
    }

    @Override
    public void execute() {
        if(order.itemsAdded())
            returnOrder(order);
        else
            System.out.println("Order Empty!");
    }
   
    public Order returnOrder(Order order){
        return order;
    }
}
