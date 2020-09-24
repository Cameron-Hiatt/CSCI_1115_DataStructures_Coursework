// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.io.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise31_01Server extends Application implements java.io.Serializable {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
    
    try {
		ServerSocket serverSocket = new ServerSocket(8000);
		
		
		
		Platform.runLater(() ->
			ta.appendText("Server started at " + new Date() + '\n'));
		
		Socket socket = serverSocket.accept();
		
		
		ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
		ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());
		
		Loan loan = new Loan();

		while(true)
		{
			ArrayList<Number> list = (ArrayList<Number>) inputFromClient.readObject();
			
			loan.setAnnualInterestRate((double) list.get(0));
			loan.setLoanAmount((double) list.get(1));
			loan.setNumberOfYears((int) list.get(2));
			
			outputToClient.writeObject(loan);
		}
		
	} catch (IOException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
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
