import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.*;
import java.io.*;

public class StockMarketController implements Initializable
{
    @FXML
    ChoiceBox stockDropdown;

    @FXML
    Button listStocksButton;
    
    @FXML
    Button logInButton;
    
    private static ArrayList<Stock> listOfAvailableStocks = new ArrayList<Stock>();

    public int sampleMethod()
    {
        return 0;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createStocks();
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
            
            for(Stock stock : listOfAvailableStocks){
                stockDropdown.getItems().add(stock.getCompany());
            }
            
          }
          bufferedReader.close();         
        }   
        catch(IOException ex) {
          ex.printStackTrace();
          System.out.println("There was a problem reading the file.");   
        }  
    
      }
}
