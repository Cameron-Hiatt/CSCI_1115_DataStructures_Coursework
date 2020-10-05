//Author: Cameron Hiatt
//Date Created:September 28th, 2020
//About: This app is an image resizing app. Specifically for resizing custom Twitch.tv emotes to their specified sizes. 
//		 The app can be expanded later on to do more beyond this but as of the date created, it is limited to Twitch.tv emote sizes.

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.awt.image.BufferedImage;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.io.*;

public class Client extends Application {
	
	/**
	 * The start method begin the GUI for the client. It creates a UI that directs the user to choose a photo they need resized, 
	 * then it sends that photo as a byte array to the server to be resized. It then receives that resized photo and dispalys it to the
	 * user in it's resized form as well as saves each one to their computer with its name as the dimensions it was resized to. 
	 */
	public void start(Stage primaryStage) {
	
	Pane mainPane = new Pane();

	FileChooser fileChooser = new FileChooser();
	FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
    FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
    FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPG files (*.jpeg)", "*.JPEG");
    fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
	
	Button selectFileBtn = new Button("Select Picture for Resizing");
	selectFileBtn.setTextAlignment(TextAlignment.CENTER);
	
	Button exit = new Button("Exit");
	
	ImageView previewImage = new ImageView();
	previewImage.setFitWidth(300);
	previewImage.setPreserveRatio(true);
	ImageView resized28x28 = new ImageView();
	ImageView resized56x56 = new ImageView();
	ImageView resized112x112 = new ImageView();
	
	Label original = new Label("Original");
	Label label28 = new Label("Resized: 28x28");
	Label label56 = new Label("Resized: 56x56");
	Label label112 = new Label("Resized: 112x112");
	
	HBox row1 = new HBox(5);
	HBox row2 = new HBox(5);
	HBox row3 = new HBox(5);
	HBox row4 = new HBox(5);
	
	VBox verticleContainer = new VBox(10);
	verticleContainer.setPadding(new Insets(0, 20, 10, 20));
	
	row1.setAlignment(Pos.CENTER);
	row2.setAlignment(Pos.CENTER);
	row3.setAlignment(Pos.CENTER);
	row4.setAlignment(Pos.CENTER);
	
	row1.getChildren().addAll(original, previewImage);
	row2.getChildren().addAll(label28, resized28x28);
	row3.getChildren().addAll(label56, resized56x56);
	row4.getChildren().addAll(label112, resized112x112);
	
	verticleContainer.getChildren().addAll(selectFileBtn, row1, row2, row3, row4, exit);
	
	verticleContainer.setAlignment(Pos.CENTER);
	verticleContainer.setPadding(new Insets(20, 20, 20, 20));
	
	mainPane.getChildren().add(verticleContainer);
	
	new Thread(() -> {
	    try
	    {
	    	Socket socket = new Socket("localHost", 8000);
	    	OutputStream outputStream = socket.getOutputStream();
	    	InputStream inputStream = socket.getInputStream();
	    	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    	
	    	while(true)
	    	{
	    		selectFileBtn.setOnAction(e -> {
	    			File file = fileChooser.showOpenDialog(null);
	    			try
	    			{
	    				BufferedImage bufferedImage = ImageIO.read(file);
	    	            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	    	            previewImage.setImage(image);
	    	            
	    	            //This block of code obtains the file extension of whatever photo is chose to be used when writing the image to byteArrayOutputStream.
	    	            String extension = "";
	    	            int index = file.getAbsolutePath().lastIndexOf('.');
	    	            int path = Math.max(file.getAbsolutePath().lastIndexOf('/'), file.getAbsolutePath().lastIndexOf('\\'));
	    	            if (index > path) 
	    	            {
	    	                extension = file.getAbsolutePath().substring(index + 1);
	    	            }
	    	            
	    	           ImageIO.write(bufferedImage, extension, byteArrayOutputStream);
	    	           
	    	           byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
	    	           outputStream.write(size);
	    	           outputStream.write(byteArrayOutputStream.toByteArray());
	    	           outputStream.flush();
	    	           
	    	           for(int i = 1; i < 4; i++)
	    	           {
	    	        	   byte[] sizeArray = new byte[4];
	   		            	inputStream.read(sizeArray);
	   		            	int size2 = ByteBuffer.wrap(sizeArray).asIntBuffer().get();

	   		            	byte[] imageArray = new byte[size2];
	   		            	inputStream.read(imageArray);
	   		            	
	   		            	if(i == 1)
	   		            	{
	   		            		BufferedImage resizedImage28x28 = ImageIO.read(new ByteArrayInputStream(imageArray));
	   		            		File f = new File("28x28.png");
	   		            		ImageIO.write(resizedImage28x28, "PNG", f);
	   		            		Image convertedImage1 = SwingFXUtils.toFXImage(resizedImage28x28, null);
	   		            		resized28x28.setImage(convertedImage1);
	   		            	}else if(i == 2)
	   		            	{
	   		            		BufferedImage resizedImage56x56 = ImageIO.read(new ByteArrayInputStream(imageArray));
	   		            		File f = new File("56x56.png");
	   		            		ImageIO.write(resizedImage56x56, "PNG", f);
	   		            		Image convertedImage2 = SwingFXUtils.toFXImage(resizedImage56x56, null);
	   		            		resized56x56.setImage(convertedImage2);
	   		            	}else
	   		            	{
	   		            		BufferedImage resizedImage112x112 = ImageIO.read(new ByteArrayInputStream(imageArray));
	   		            		File f = new File("112x112.png");
	   		            		ImageIO.write(resizedImage112x112, "PNG", f);
	   		            		Image convertedImage3 = SwingFXUtils.toFXImage(resizedImage112x112, null);
	   		            		resized112x112.setImage(convertedImage3);
	   		            	}
	    	           }

	    	            
	    			}catch(IOException ex)
	    			{
	    				ex.printStackTrace();
	    			}
	    		});
	    		
	    		exit.setOnAction(e -> {
	    			try {
						socket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			System.exit(0);
	    		});
	    	}
	    }catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
    }).start();
 
    // Create a scene and place it in the stage
    Scene scene = new Scene(mainPane, 400, 550);
    primaryStage.setTitle("Twitch Emote Resizer"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /**
   * Main method needed for this IDE
   * 
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
