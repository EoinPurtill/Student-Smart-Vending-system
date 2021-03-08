package vendingMachine;

public class DrinkStock implements Stockinterface {

    @Override
    public String[] getstock() {
        String[] drink = new String[]{"Water"};
        return drink;
    }

    
}
