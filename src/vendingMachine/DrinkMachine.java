package vendingMachine;

public class DrinkMachine extends VendingMachine2 {
    public DrinkMachine(Stockinterface stock){
        super(stock);
    }

    @Override
    public String createMachine() {

        return "Stock";
    }
}
