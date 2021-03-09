package vendingMachine;

public abstract class VendingMachine2 {

    protected Stockinterface stock;



    public VendingMachine2(Stockinterface stock) {
        this.stock = stock;
    }

    abstract public String createMachine();

}
