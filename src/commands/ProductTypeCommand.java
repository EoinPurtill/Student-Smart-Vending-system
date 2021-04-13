package commands;

import java.beans.PropertyDescriptor;

import product.ProductDescriptorFacade;

public class ProductTypeCommand extends Command implements CommandInterface{
    
    ProductDescriptorFacade pd;

    public void execute(){
        pd = new ProductDescriptorFacade();
        System.out.println(pd.describe());
    }
}
