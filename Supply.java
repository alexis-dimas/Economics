public class Supply {
   private double supply;
   private double supplySlope;
   
   public Supply(double userS, double userSS) {
      this.supply = userS;
      this.supplySlope = userSS;
   }
   
   public double getS() {
      return supply;
   }
   
   public double getSS() {
      return supplySlope;
   }
}
