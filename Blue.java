import java.util.*;

public class Blue extends Stock
{
    private boolean votingRights;
    
    // Blue contstructor
    public Blue(String name, double price)
    {
        super(name, price);
        votingRights = true;
    }

    public boolean getVotingRights()
    {
        return votingRights;
    }
    
    // fluctuates the price of the stock
    public void fluctuatePrice(){
        Random rand = new Random();
        double price = getPrice();
        
        int n = rand.nextInt(100) + 1;
        
        if (n <= 50) {
            price *= 0.95;
        }
        else {
            price *= 1.05;
        }
        System.out.println("The new price for " + getName() + " stock is " + price);
    }
}
