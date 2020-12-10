package product;

public class ProductFactory {
	
   //use getProduct method to get object of type Product 
   public Product getProduct(String productType){
      if(productType == null){
         return null;
      }		
      if(productType.equalsIgnoreCase("TREAT")){
         return new Treat();
         
      } else if(productType.equalsIgnoreCase("SNACK")){
         return new SNACK();
         
      } else if(productType.equalsIgnoreCase("FRUIT")){
         return new FRUIT();

      } else if(productType.equalsIgnoreCase("DRINK")){
        return new DRINK();
      }

      
      return null;
   }
}