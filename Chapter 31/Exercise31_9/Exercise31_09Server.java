import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise31_09Server extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  DataOutputStream outputToClient = null;
  DataInputStream inputFromClient = null;
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    //taClient.setDisable(true);
    taServer.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    taClient.setOnKeyPressed(e -> {
    	if(e.getCode() == KeyCode.ENTER)
    	{
    		try
    		{
    			String message = taClient.getText();
    			outputToClient.writeUTF(message);
    			outputToClient.flush();
    			taServer.appendText("S: " + taClient.getText() + "\n");
        		taClient.clear();
        		e.consume();
    		}catch(IOException ex)
    		{
    			ex.printStackTrace();
    		}
    	}
    });
    
    new Thread(() -> {
	    try
	    {
	    	ServerSocket serverSocket = new ServerSocket(8000);
	    	
	    	Socket socket = serverSocket.accept();
	    	
	    	inputFromClient = new DataInputStream(socket.getInputStream());
	    	outputToClient = new DataOutputStream(socket.getOutputStream());
	    	
	    	while(true)
	    	{
	    		String input = inputFromClient.readUTF();
	    		
	    		taServer.appendText("C:" + input + "\n");
	    	}
	    }catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
    }).start();
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // To complete later
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
