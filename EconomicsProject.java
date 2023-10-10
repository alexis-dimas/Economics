import java.util.*;

public class EconomicsProject {
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      
      // Introduction Message.
      introMessage();
      
      String option = "";
      while (!option.equalsIgnoreCase("q")) {
         System.out.println("How many functions are you entering? ");
         System.out.print("(1) Function, (2) Functions, (Q)uit? ");
         option = console.next();
         System.out.println();
         
         if (option.equals("1")) {
            boolean priceTaker = false;
            Functions func = setFunction(console, priceTaker);
            AvgVariableCost avc = avcFunction(console);
            double fixedCosts = totalFixedCost(console);
            MarginalCost mc = printFunctions(func, avc);
            MaxValues max = profitMax(func, mc, priceTaker);
            calcElasticity(func, max);
            calcProfit(max, avc, fixedCosts, priceTaker);
            shutdown(max, avc, priceTaker);
         } else if (option.equals("2")) {
            boolean priceTaker = true;
            Functions func = setFunction(console, priceTaker);
            AvgVariableCost avc = avcFunction(console);
            double fixedCosts = totalFixedCost(console);
            MarginalCost mc = printFunctions(func, avc);
            MaxValues max = profitMax(func, mc, priceTaker);
            calcElasticity(func, max);
            calcProfit(max, avc, fixedCosts, priceTaker);
            shutdown(max, avc, priceTaker);
         }
      }
   }
   
   // Prints the introduction message.
   public static void introMessage() {
      System.out.println("Hello and welcome!");
      System.out.println();
      System.out.println("This is an economics project created by Alexis Dimas.");
      System.out.println("Enter information requested by the prompts to receive the");
      System.out.println("various functions and answers to some problems in B ECON 300!");
      System.out.println();
   }
   
   // Prompts the user to enter the direct demand function constant 
   // and coefficient. Will also prompt the user to enter the direct
   // supply function contant and coefficient if the firm is a price
   // taker.
   // Returns the Functions object.
   // Parameters:
   //    Scanner console - allows user input
   //    boolean priceTaker - determines the type of firm
   public static Functions setFunction(Scanner console, boolean priceTaker) {
      System.out.println("Enter the direct demand function:");
      System.out.print("Constant  = ");
      double demandConstant = console.nextDouble();
      System.out.print("Coefficient = ");
      double demandSlope = console.nextDouble();
      double supplyConstant = 0;
      double supplySlope = 0;
      
      if (priceTaker) {
         System.out.println();
         System.out.println("Enter the direct supply function:");
         System.out.print("Constant = ");
         supplyConstant = console.nextDouble();
         System.out.print("Coefficient = ");
         supplySlope = console.nextDouble();
      }
      
      System.out.println();
      Functions func = new Functions(demandConstant, demandSlope, 
                                     supplyConstant, supplySlope);
      return func;
   }
   
   // Prompts the user to enter an average variable cost function.
   // Creates a new object called AvgVariableCost.
   // Returns the AvgVariableCost object.
   // Parameters:
   //    Scanner console - allows user input
   public static AvgVariableCost avcFunction(Scanner console) {
      System.out.println("Enter an average variable cost function:");
      System.out.print("a = ");
      double a = console.nextDouble();
      System.out.print("b = ");
      double b = console.nextDouble();
      System.out.print("c = ");
      double c = console.nextDouble();
      System.out.println();
      AvgVariableCost avc = new AvgVariableCost(a, b, c); 
      return avc;
   }
   
   // Prompts the user to enter total fixed costs.
   // Returns total fixed costs.
   // Parameters:
   //    Scanner console - allows user input
   public static double totalFixedCost(Scanner console) {
      System.out.println("Enter total fixed costs:");
      System.out.print("TFC = ");
      double fixedCosts = console.nextDouble();
      System.out.println();
      return fixedCosts;
   }
   
   // Prints direct, inverse, TR, MR, AVC, VC, and MC functions.
   // Creates a new object called MarginalCost.
   // Returns the MarginalCost object.
   // Parameters:
   //    Functions func - used to access fields in the class
   //    AvgVariableCost avc - used to access fields in the class
   public static MarginalCost printFunctions(Functions func, AvgVariableCost avc) {
      System.out.println("Direct Demand Function: Qd = " + func.getDemConstant() + 
               " - " + func.getAbsDemSlope() + "P");
      System.out.println("Inverse Demand Function: P = " + func.getInvIntercept() + 
               " - " + func.getInvSlope() + "Q");
      System.out.println("Total Revenue Function: TR = " + func.getInvIntercept() + 
               "Q - " + func.getInvSlope() + "Q^2");
      System.out.println("Marginal Revenue Function: MR = " + func.getInvIntercept() + 
               " - " + func.getMarRevSlope() + "Q");  
      System.out.println();
      
      MarginalCost mc = new MarginalCost(avc.getA(), avc.getB(), avc.getC());
      
      System.out.println("AVC = " + avc.getC() + " - " + Math.abs(avc.getB()) + 
               "Q + " + avc.getA() + "Q^2");
      System.out.println("VC = " + avc.getC() + "Q - " + Math.abs(avc.getB()) + 
               "Q^2 + " + avc.getA() + "Q^3");
      System.out.println("MC = " + mc.getC() + " - " + Math.abs(mc.getB()) + 
               "Q + " + mc.getA() + "Q^2");  
      System.out.println();
      return mc;
   }
   
   // Determines the profit maximizing price and quantity.
   // Creates a new object called MaxValues.
   // Returns the MaxValues object.
   // Parameters:
   //    Functions func - used to access fields in the class
   //    MarginalCost mc - used to access fields in the class
   //    boolean priceTaker - determines the type of firm
   public static MaxValues profitMax(Functions func, MarginalCost mc, boolean priceTaker) {
      double profitMaxPrice = 0;
      double profitMaxQuantity = 0;
      
      if (!priceTaker) {
         mc.setB(func.getMarRevSlope());
         mc.setC(func.getInvIntercept());
      } else if (priceTaker) {
         mc.setC(func.getEquilibriumPrice());
         profitMaxPrice = func.getEquilibriumPrice();
         System.out.println("Profit Maximizing Price = $" + profitMaxPrice);
      }
        
      // This...is the quadratic formula.       
      profitMaxQuantity = (Math.abs(mc.getB()) + Math.sqrt(Math.pow(mc.getB(), 2) - 
               (4 * mc.getA() * mc.getC()))) / (2 * mc.getA());         
      profitMaxQuantity = round(profitMaxQuantity);
      
      if (!priceTaker) {
         profitMaxPrice = func.getInvIntercept() - func.getInvSlope() * profitMaxQuantity;
         System.out.println("Profit Maximizing Price = $" + profitMaxPrice);
      }
      
      System.out.println("Profit Maximizing Quantity = " + profitMaxQuantity + " units.");
      System.out.println();      
      MaxValues max = new MaxValues(profitMaxPrice, profitMaxQuantity);
      return max;
   }
   
   // Calculates price elasticity of demand.
   // Parameters:
   //    Functions func - used to access fields in the class
   //    MaxValues max - used to access fields in the class
   public static void calcElasticity(Functions func, MaxValues max) {
      double quantityDemanded = func.getDemConstant() + func.getDemSlope() * max.getPrice();
      double elasticity = func.getDemSlope() * (max.getPrice() / quantityDemanded);
      elasticity = round(elasticity);
      System.out.println("Price Elasticity of Demand = " + elasticity);
      
      if (elasticity > -1) {
         System.out.println("Demand is inelastic at this price.");
      } else if (elasticity == -1) {
         System.out.println("Demand is unitary elastic at this price.");
      } else {
         System.out.println("Demand is elastic at this price.");
      }
      
      System.out.println();
   }
   
   // Calculates total revenue, total cost, and profit.
   // Parameters:
   //    MaxValues max - used to access fields in the class
   //    AvgVariableCost avc - used to access fields in the class
   //    double fixedCosts - total fixed costs
   //    boolean priceTaker - determines the type of firm
   public static void calcProfit(MaxValues max, AvgVariableCost avc, 
                                 double fixedCosts, boolean priceTaker) {
      double totalRevenue = max.getPrice() * max.getQuantity();
      
      if (!priceTaker) {
         avc.setQuantity(max.getQuantity());
      }
      
      double totalVariableCost = avc.getAVC() * max.getQuantity();
      double totalCosts = totalVariableCost + fixedCosts;
      double profit = totalRevenue - totalCosts;
      
      System.out.println("Total Revenue = $" + round(totalRevenue));
      System.out.println("Total Cost = $" + round(totalCosts));
      
      if (profit >= 0) {
         System.out.println("Profit = $" + round(profit));
      } else {
         System.out.println("Profit = -$" + Math.abs(round(profit)));
      }

      System.out.println();
   }
   
   // Determines if the firm should shut down in the short run.
   // Parameters:
   //    MaxValues max - used to access fields in the class
   //    AvgVariableCost avc - used to access fields in the class
   //    boolean priceTaker - determines the type of firm
   public static void shutdown(MaxValues max, AvgVariableCost avc, boolean priceTaker) {
      if (!priceTaker) {
         avc.setQuantity(max.getQuantity());
      }
      
      if (max.getPrice() >= avc.getAVC()) {
         System.out.println("$" + max.getPrice() + " >= $" + avc.getAVC());
         System.out.println();
         System.out.println("The firm should continue production since price is");
         System.out.println("greater than or equal to average variable cost.");
         System.out.println();
         System.out.println("This means that the firm can cover all of its");
         System.out.println("variable costs and some/all of its fixed costs.");
      } else {
         System.out.println("$" + max.getPrice() + " < $" + avc.getAVC());
         System.out.println();
         System.out.println("The firm should shut down because price is less than");
         System.out.println("average variable cost.");
         System.out.println();
         System.out.println("This means that the firm is losing all of its fixed");
         System.out.println("costs and some/all of its variable costs. The firm"); 
         System.out.println("would be better off shutting down and lose only its"); 
         System.out.println("fixed costs.");
      }
      
      System.out.println();
   }
   
   // Rounds a number to two decimal places.
   // Returns the rounded number.
   // Parameters:
   //    double num - number to be rounded
   public static double round(double num) {
      double roundedNum = Math.round(num * 100.0) / 100.0;
      return roundedNum;
   }
}
