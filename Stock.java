import java.util.*;

public class Stock
{
    private double price;
    private String name;

    // stock constructor
    public Stock(String company, double price){
        this.name = company;
        this.price = price;
    }

    public double getPrice(){
        return price;
    }
    
    public String getName(){
        return name;
    }
    
    //fluctuates the price of the stock
    public void fluctuatePrice(){
        Random rand = new Random();
        
        int n = rand.nextInt(100) + 1;
        
        if (n <= 50) {
            price *= 0.97;
        }
        else {
            price *= 1.03;
        }
        System.out.println("The new price for " + name + " stock is " + price);
    }
    
}
