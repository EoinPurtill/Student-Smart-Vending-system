package product;

public class ProductFactory {
	
   //use getProduct method to get object of type Product 
   public Product getProduct(String productType, String d, double p){
      if(productType == null){
         return null;
      }		
      if(productType.equalsIgnoreCase("TREAT")){
         return new Treat(d, p);
         
      } else if(productType.equalsIgnoreCase("SNACK")){
         return new Snack(d, p);
         
      } else if(productType.equalsIgnoreCase("FRUIT")){
         return new Fruit(d, p);

      } else if(productType.equalsIgnoreCase("DRINK")){
        return new Drink(d, p);
      }

      return null;
   }
}







