import java.util.*;

public class Orange extends Stock
{
    private double dividend;

    // Orange constructor
    public Orange(String name, double price)
    {
        super(name, price);
        dividend = 1.50;
    }
    
    // returns the dividend amount owed to a user
    public double getDividend(int amountOfShares)
    {
        return dividend * amountOfShares;
    }
    
    // fluctuates the price of the stock
    public void fluctuatePrice(){
        Random rand = new Random();
        double price = getPrice();
        
        int n = rand.nextInt(100) + 1;
        
        if (n <= 50) {
            price *= 0.98;
        }
        else {
            price *= 1.02;
        }
        System.out.println("The new price for " + getName() + " stock is " + price);
    }
}
