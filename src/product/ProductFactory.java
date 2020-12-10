package product;

public class ProductFactory {
	
   //use getProduct method to get object of type Product 
   public Product getProduct(String productType){
      if(productType == null){
         return null;
      }		
      if(productType.equalsIgnoreCase("TREAT")){
         return new Treat("treat", 0.0);
         
      } else if(productType.equalsIgnoreCase("SNACK")){
         return new Snack("snack", 0.0);
         
      } else if(productType.equalsIgnoreCase("FRUIT")){
         return new Fruit("fruit", 0.0);

      } else if(productType.equalsIgnoreCase("DRINK")){
        return new Drink("drink", 0.0);
      }

      
      return null;
   }
}