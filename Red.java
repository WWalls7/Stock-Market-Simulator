import java.util.*;

public class Red extends Stock
{
    private int limit;

    // Red constructor
    public Red(String name, double price)
    {
        super(name, price);
        limit = 50;
    }

    public int getLimit()
    {
        return limit;
    }
    
    public void updateLimit(){
        limit--;
    }
    
    // fluctuates the price of the stock
    public void fluctuatePrice(){
        Random rand = new Random();
        double price = getPrice();
        
        int n = rand.nextInt(100) + 1;
        
        if (n <= 50) {
            price *= 0.99;
        }
        else {
            price *= 1.01;
        }
        System.out.println("The new price for " + getName() + " stock is " + price);
    }
}
