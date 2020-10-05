import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

import org.junit.Test;

public class ServerTest {

	@Test
	public void testResize() {
		try {
			BufferedImage image1 = ImageIO.read(new File("test.jpeg"));
			BufferedImage image2 = ImageIO.read(new File("test4.png"));
			BufferedImage image3 = ImageIO.read(new File("test.jpeg"));
			BufferedImage image1Resized = ImageIO.read(new File("test28x28.png"));
			BufferedImage image2Resized = ImageIO.read(new File("test456x56.png"));
			BufferedImage image3Resized = ImageIO.read(new File("test112x112.png"));
			
			BufferedImage image1Tested = Server.resize(image1, 1);
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
			
			ImageIO.write(image1Tested, "png", byteArrayOutputStream);
			byte[] array1 = byteArrayOutputStream.toByteArray();
			
			ImageIO.write(image1Resized, "png", byteArrayOutputStream2);
			byte[] array1Test = byteArrayOutputStream2.toByteArray();
			
			ByteBuffer.wrap(array1).getDouble();
			
			assertEquals(ByteBuffer.wrap(array1).getDouble(), ByteBuffer.wrap(array1Test).getDouble(), 0);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
