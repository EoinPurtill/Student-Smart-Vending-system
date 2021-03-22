package commands;

public class MultiOrderCancelCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Order cancelled\n\n");
        
    }
    
}
