package commands;

public class MultiOrderCancelCommand extends Command implements CommandInterface{

    @Override
    public void execute() {
        System.out.println("Order cancelled\n\n");
        
    }
    
}
