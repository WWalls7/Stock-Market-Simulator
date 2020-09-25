import java.util.*;
import java.io.*;

public class Market
{
private static ArrayList<Stock> listOfAvailableStocks = new ArrayList<Stock>();
private static User loggedInUser;

public static void main(String[] args){
createStocks();
boolean exitProgram = false;
Scanner scan = new Scanner(System.in);

// System.out.println("Do you have a user? (y/n)");
// String input = scan.nextLine();

createUser();

System.out.println("What would you like to do?");

int counter = 0;

// loops through each round until the user quits
while(!exitProgram){
  counter++;
  System.out.println("\nRound: "+ counter);
  System.out.println("\nYour account balance is: "+ loggedInUser.getWalletBalance());
  System.out.println("\nThe available options are:");
  System.out.println("\n1: Deposit to virtual wallet");
  System.out.println("2: List available stocks for purchase");
  System.out.println("3: Buy stocks");
  System.out.println("4: Exit program");
  String input = scan.nextLine();
  
  if(input.equals("1")){
    depositToAccount();
  }
  else if(input.equals("2")){
    listAvailableStocks();
  }
  else if(input.equals("3")){
    buyAvailableStock();
  }
  else if(input.equals("4")){
    System.out.println("Goodbye...");
    exitProgram = true;
    System.exit(0);
  }
  else{
    System.out.println("ERROR - We don't recognize that option. Please try again");
    continue;
  }
  
  Red red = (Red)listOfAvailableStocks.get(0); 
  Blue blue = (Blue)listOfAvailableStocks.get(1);
  Orange orange = (Orange)listOfAvailableStocks.get(2); 
  
  // fluctuates the price of each stock type after each round
  red.fluctuatePrice();
  blue.fluctuatePrice();
  orange.fluctuatePrice();
  
  // pays dividends for Orange stocks after every 3rd round
  if(counter % 3 == 0){
    int amountOrange = 0;
    ArrayList<Stock> boughtStocks = loggedInUser.getStocks();
    for(int j = 0; j< boughtStocks.size(); j++){
      if(boughtStocks.get(j).getName().equals("Orange")){
        amountOrange++;
      }
    }
    loggedInUser.depositToWallet(orange.getDividend(amountOrange));
    System.out.println("\nDividend earned from Orange stocks: "+orange.getDividend(amountOrange));
  }
}
}

//creates the stocks
private static void createStocks(){
String fileName = "stockPrices.txt"; 
String line = null;

try {
      FileReader fileReader = new FileReader(fileName);
      
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      while((line = bufferedReader.readLine()) != null) {
        String[] components = line.split(" ");
        
        listOfAvailableStocks.add(new Red("Red", Integer.parseInt(components[0])));
        listOfAvailableStocks.add(new Blue("Blue", Integer.parseInt(components[1])));
        listOfAvailableStocks.add(new Orange("Orange", Integer.parseInt(components[2])));
        
      }
      bufferedReader.close();         
    }   
    catch(IOException ex) {
      ex.printStackTrace();
      System.out.println("There was a problem reading the file.");   
    }  

  }
  
  //creates the user
  private static void createUser(){
    Scanner scan = new Scanner(System.in);
    System.out.println("Lets create a user. What is your name");
    String name = scan.nextLine();
    
    User user = new User(name);
    loggedInUser = user;
    System.out.println("We've created a new user with the name " + name);
  }
  
  // deposits money into a user's account
  private static void depositToAccount(){
    Scanner scan = new Scanner(System.in);
    System.out.println("How much would you like to deposit");
    
    try{
      double amount = scan.nextDouble();
      loggedInUser.depositToWallet(amount);
    }
    catch(InputMismatchException ex){
      System.out.println("Please enter a number");
      depositToAccount();
    }
    
    System.out.println("\nThe new balance of your wallet is: " + loggedInUser.getWalletBalance()+"\n");
  }
  
  // lists the stocks available for purchase
  public static void listAvailableStocks(){
    System.out.println("");
    for(int i = 0; i < listOfAvailableStocks.size(); i++){
      System.out.println(i+1 + ": " + listOfAvailableStocks.get(i).getName());
    }
    System.out.println("");
  }
  
  // method for purchasing stocks
  public static void buyAvailableStock(){
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the number of the stock you wish to buy");
    listAvailableStocks();
    
    try{
      int input = scan.nextInt();
      try{
        int availableStockSlots = loggedInUser.buyStock(listOfAvailableStocks.get(input - 1));
        if(availableStockSlots == 0){
          System.out.println("You have reached your stock purchase limit.");
        }
      }
      catch(ArrayIndexOutOfBoundsException ex){
        System.out.println("Please enter a number 1-3");
        buyAvailableStock();
      }
      
    }
    catch(InputMismatchException ex){
      System.out.println("Please enter a number 1-3");
      buyAvailableStock();
    }
  }
}
