// A class to represent the profit maximizing values in the client
public class MaxValues {
   private double maxPrice;
   private double maxQuantity;
   
   // Constructs the profit maximizing values.
   public MaxValues(double maxP, double maxQ) {
      this.maxPrice = maxP;
      this.maxQuantity = maxQ;
   }
   
   // Returns the profit maximizing price.
   public double getPrice() {
      return maxPrice;
   }
   
   // Returns the profit maximizing quantity.
   public double getQuantity() {
      return maxQuantity;
   }
}
