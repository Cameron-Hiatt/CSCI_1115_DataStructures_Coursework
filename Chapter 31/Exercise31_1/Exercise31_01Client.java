// Exercise31_01Client.java: The client sends the input to the server and receives
// result back from the server
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Exercise31_01Client extends Application implements java.io.Serializable{
  // Text field for receiving radius
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfNumOfYears = new TextField();
  private TextField tfLoanAmount = new TextField();
  private Button btSubmit= new Button("Submit");
  DoubleStringConverter doubleConverter = new DoubleStringConverter();

  // Text area to display contents
  private TextArea ta = new TextArea();
  String host = "localhost";
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Annual Interest Rate"), 0, 0);
    gridPane.add(new Label("Number Of Years"), 0, 1);
    gridPane.add(new Label("Loan Amount"), 0, 2);
    gridPane.add(tfAnnualInterestRate, 1, 0);
    gridPane.add(tfNumOfYears, 1, 1);
    gridPane.add(tfLoanAmount, 1, 2);
    gridPane.add(btSubmit, 2, 1);
     
    
    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
    tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);
    
    tfLoanAmount.setPrefColumnCount(5);
    tfNumOfYears.setPrefColumnCount(5);
    tfLoanAmount.setPrefColumnCount(5);
            
    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setTop(gridPane);
    
    btSubmit.setOnMouseClicked(e -> {
    	double AnnualIR = doubleConverter.fromString(tfAnnualInterestRate.getText());
    	double LoanAmount = doubleConverter.fromString(tfLoanAmount.getText());
    	int numYears = Integer.parseInt(tfNumOfYears.getText());
    	
    	ArrayList<Number> list = new ArrayList<Number>();
    	
    	list.add(AnnualIR);
    	list.add(LoanAmount);
    	list.add(numYears);
    	
    	try
    	{
    		Socket socket = new Socket("localHost", 8000);
    		
    		ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
    		ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
    		
    		toServer.writeObject(list);
    		toServer.flush();
    		
    		Loan loan = (Loan) fromServer.readObject();
    		
    		
    	}catch(IOException | ClassNotFoundException ex)
    	{
    		ex.printStackTrace();
    	}
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
