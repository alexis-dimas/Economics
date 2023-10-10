public class Demand {
   private double demand;
   private double demandSlope;
   
   public Demand(double userD, double userDS) {
      this.demand = userD;
      this.demandSlope = userDS;
   }
   
   public double getD() {
      return demand;
   }
   
   public double getDS() {
      return demandSlope;
   }
   
   public double getAbsDSlope() {
      return Math.abs(demandSlope);
   }
   
   public double getIInt() {
      return demand / Math.abs(demandSlope);
   }
   
   public double getISlope() {
      return 1 / Math.abs(demandSlope);
   }
}
