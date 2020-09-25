import java.util.*;

public class User
    {
    private String name;
    private double wallet;
    private ArrayList<Stock> stocks;
    private int availableStockSlots;
    
    // user constructor
    public User(String name)
    {
        this.name = name;
        this.wallet = 0;
        stocks = new ArrayList<Stock>();
        availableStockSlots = 10;
    }
    
    // deposits an amount to the user's wallet
    public void depositToWallet(double amount)
    {
        this.wallet += amount;
    }
    
    // returns the user's wallet balance
    public double getWalletBalance()
    {
        return this.wallet;
    }
    
    public String getName(){
        return this.name;
    }
    
    // method for purchasing a stock and updating the amount the user owns
    public int buyStock(Stock stock){
        if(availableStockSlots == 0){
            System.out.println("ERROR - You already own the maximum number of stocks");
        }
        
        if(payForStock(stock)){
            int newAvailable = 10 - availableStockSlots;
            stocks.add(stock);
            System.out.println("You now own a stock for " + stock.getName());
            if(stock.getName().equals("Red")){
                 Red red = (Red)stock; 
                 red.updateLimit();
                 System.out.println("Red stocks left available for purchase: "+red.getLimit());
            }
            return newAvailable;
        }
        return availableStockSlots;
    }
    
    // takes money out of the user's wallet for a stock that has been purchased
    public boolean payForStock(Stock stock){
        if(stock.getPrice() > this.wallet){
            System.out.println("ERROR - You do not have enough money in your wallet\n");
            return false;
        }
        
        this.wallet -= stock.getPrice();
        return true;
    }
    
    public ArrayList<Stock> getStocks(){
        return stocks;
    }
}
