// A class to represent the MC function in the client
public class MarginalCost {
   private double a;
   private double b;
   private double c;
   
   // Constructs coefficients for the MC function.
   // Parameters:
   //    double a - number to be used as the coefficient
   //    double b - number to be used as the coefficient
   //    double c - number to be used as the coefficient
   public MarginalCost(double a, double b, double c) {
      this.a = 3 * a;
      this.b = 2 * b;
      this.c = c;
   }
   
   // Sets a new b coefficient.
   // Parameters:
   //    double newB - the new coefficient to be used
   public void setB(double newB) {
      this.b = b + newB;
   }
   
   // Sets a new c coefficient.
   // Parameters:
   //    double newC - the new coefficinet to be used
   public void setC(double newC) {
      this.c = c - newC;
   }
   
   // Returns the a coefficient for the MC function.
   public double getA() {
      return a;
   }
   
   // Returns the b coefficient for the MC function.
   public double getB() {
      return b;
   }
   
   // Returns the c coefficient for the MC function.
   public double getC() {
      return c;
   }
}
