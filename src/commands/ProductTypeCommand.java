package commands;

import product.ProductDescriptorFacade;

public class ProductTypeCommand extends Command implements CommandInterface{
    
    ProductDescriptorFacade pdf;

    public void execute(){
        pdf = new ProductDescriptorFacade();
        System.out.println(pdf.describe());
    }
}
