// A class to 
public class Functions {
   private double demandConstant;
   private double demandSlope;
   private double supplyConstant;
   private double supplySlope;
   private double price;
   private double quantity;
   private double equilibriumPrice;

   // Constructs a new function???
   public Functions(double dC, double dS, double sC, double sS) {
      this.demandConstant = dC;
      this.demandSlope = dS;
      this.supplyConstant = sC;
      this.supplySlope = sS;
      this.price = Math.abs(dS - sS);
      this.quantity = Math.abs(dC - sC);
      this.equilibriumPrice = round(quantity / price);
   }
   
   // Returns the constant of the direct demand function.
   public double getDemConstant() {
      return demandConstant;
   }
   
   // Returns the slope of the direct demand function.
   public double getDemSlope() {
      return demandSlope;
   }
   
   // Returns the absolute value of the slope.
   public double getAbsDemSlope() {
      return Math.abs(demandSlope);
   }
   
   // Returns the y-intercept of the inverse demand function.
   public double getInvIntercept() {
      return demandConstant / getAbsDemSlope();
   }
   
   // Returns the slope of the inverse demand function.
   public double getInvSlope() {
      return 1 / getAbsDemSlope();
   }
   
   // Returns the slope of the marginal revenue function.
   public double getMarRevSlope() {
      return 2 * getInvSlope();
   }
   
   // Returns the market equilibrium price. 
   public double getEquilibriumPrice() {
      return equilibriumPrice;
   }

   // Returns the market equilibrium quantity.
   public double getEquilibriumQuantity() {
      double quantityFromDemand = demandConstant - Math.abs(demandSlope * equilibriumPrice);
      return round(quantityFromDemand);
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
