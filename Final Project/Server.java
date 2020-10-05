import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

public class Server 
{
	/**
	 * The Main Method of class Server simply begins the server side of the program. It begins a new thread,
	 * which then it creates a server to handle the receiving and calls the resize method to resize buffered images. 
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		new Thread(() -> {
		    try
		    {
		    	ServerSocket serverSocket = new ServerSocket(8000);
		    	
		    	Socket socket = serverSocket.accept();
		    	
		    	InputStream inputStream = socket.getInputStream();
		    	OutputStream outputStream = socket.getOutputStream();
		    	ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
		    	ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
		    	ByteArrayOutputStream baos3 = new ByteArrayOutputStream();

		    	while(true)
		    	{
		    		//I also had much help for this portion.
		    		//Found here: https://stackoverflow.com/questions/25086868/how-to-send-images-through-sockets-in-java
		    		//I was able to understand well enough how to send it after researching but receiving an image in form of byte array
		    		//would've taken me a long long time. Credit to the users in that thread for lines 59-64, helped me out a ton.
		    		byte[] sizeArray = new byte[4];
		            inputStream.read(sizeArray);
		            int size = ByteBuffer.wrap(sizeArray).asIntBuffer().get();

		            byte[] imageArray = new byte[size];
		            inputStream.read(imageArray);

		            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageArray));
		            
	            	BufferedImage image1 = resize(bufferedImage, 1);
	            	
	            	ImageIO.write(image1, "png", baos1);
	    	           
	    	        byte[] size1 = ByteBuffer.allocate(4).putInt(baos1.size()).array();
	    	        outputStream.write(size1);
	    	        outputStream.write(baos1.toByteArray());
	    	        outputStream.flush();
	    	        
	    	        BufferedImage image2 = resize(bufferedImage, 2);
	            	
	            	ImageIO.write(image2, "png", baos2);
	    	           
	    	        byte[] size2 = ByteBuffer.allocate(4).putInt(baos2.size()).array();
	    	        outputStream.write(size2);
	    	        outputStream.write(baos2.toByteArray());
	    	        outputStream.flush();
	    	        
	    	        BufferedImage image3 = resize(bufferedImage, 3);
	            	
	            	ImageIO.write(image3, "png", baos3);
	    	           
	    	        byte[] size3 = ByteBuffer.allocate(4).putInt(baos3.size()).array();
	    	        outputStream.write(size3);
	    	        outputStream.write(baos3.toByteArray());
	    	        outputStream.flush();
		    	}
		    }catch(IOException e)
		    {
		    	e.printStackTrace();
		    }
	    }).start();
	}
	
	/**
	 * This method is for resizing an image to the proper size Twitch.tv asks for. The method used here I learned how to do here:
	 * https://www.baeldung.com/java-resize-image#imgscalr
	 * https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
	 * 
	 * @param originalBufferedImage
	 * @param sizeIndicator
	 * @return BufferedImage bimage
	 */
	public static BufferedImage resize(BufferedImage originalBufferedImage, int sizeIndicator)
	{
		int small = 28;
		int med = 56;
		int large = 112;
		
		if(sizeIndicator == 1)
		{
			Image resultingImage = originalBufferedImage.getScaledInstance(small, small, Image.SCALE_DEFAULT);
		    BufferedImage outputImage = new BufferedImage(small, small, BufferedImage.TYPE_INT_RGB);
		    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		    // Create a buffered image with transparency
		    BufferedImage bimage = new BufferedImage(resultingImage.getWidth(null), resultingImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		    // Draw the image on to the buffered image
		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(resultingImage, 0, 0, null);
		    bGr.dispose();

		    return bimage;
		}else if(sizeIndicator == 2)
		{
			Image resultingImage = originalBufferedImage.getScaledInstance(med, med, Image.SCALE_DEFAULT);
		    BufferedImage outputImage = new BufferedImage(med, med, BufferedImage.TYPE_INT_RGB);
		    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		    // Create a buffered image with transparency
		    BufferedImage bimage = new BufferedImage(resultingImage.getWidth(null), resultingImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		    // Draw the image on to the buffered image
		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(resultingImage, 0, 0, null);
		    bGr.dispose();

		    return bimage;
		}else 
		{
			Image resultingImage = originalBufferedImage.getScaledInstance(large, large, Image.SCALE_DEFAULT);
		    BufferedImage outputImage = new BufferedImage(large, large, BufferedImage.TYPE_INT_RGB);
		    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		    // Create a buffered image with transparency
		    BufferedImage bimage = new BufferedImage(resultingImage.getWidth(null), resultingImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		    // Draw the image on to the buffered image
		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(resultingImage, 0, 0, null);
		    bGr.dispose();

		    return bimage;
		}
	}
}

