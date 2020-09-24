import javafx.application.Application;
import static javafx.application.Application.launch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise31_09Client extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  DataOutputStream outputToServer = null;
  DataInputStream inputFromServer = null;
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    //taServer.setDisable(true);
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
    			outputToServer.writeUTF(message);
    			outputToServer.flush();
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
	    	Socket socket = new Socket("localHost", 8000);
	    	
	    	inputFromServer = new DataInputStream(socket.getInputStream());
	    	outputToServer = new DataOutputStream(socket.getOutputStream());
	    	
	    	while(true)
	    	{
	    		String input = inputFromServer.readUTF();
	    		
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
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
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
