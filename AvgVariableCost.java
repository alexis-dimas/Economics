// A class to represent the AVC function in the client
public class AvgVariableCost {
   private double a;
   private double b;
   private double c;
   private double quantity;
   
   // Constructs coefficients for the AVC function.
   // Parameters:
   //    double a - number to be used as the coefficient
   //    double b - number to be used as the coefficient
   //    double c - number to be used as the coefficient
   public AvgVariableCost(double a, double b, double c) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.quantity = round(b * -1 / (2 * a));
   }
   
   // Returns the a coefficient of the AVC function.
   public double getA() {
      return a;
   }
   
   // Returns the b coefficient of the AVC function.
   public double getB() {
      return b;
   }
   
   // Returns the c coefficient of the AVC function.
   public double getC() {
      return c;
   }
   
   // Sets a new quantity.
   // Parameters:
   //    double newQuantity - the new quantity to be used
   public void setQuantity(double newQuantity) {
      this.quantity = newQuantity;
   }
   
   // Returns the quantity used to calculate AVC.
   public double getQuantity() {
      return quantity;
   }
   
   // Returns the average variable cost.
   public double getAVC() {
      return round(c + b * quantity + a * Math.pow(quantity, 2));
   }
   
   // Rounds a number to two decimal places.
   // Returns the rounded number.
   // Parameters:
   //    double num - number to be rounded
   public double round(double num) {
      double roundedNum = Math.round(num * 100.0) / 100.0;
      return roundedNum;
   }
}
