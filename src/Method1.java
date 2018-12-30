import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Deep Fried Manipulation Method 1: Saturation and Brightness Boost
 * 
 * @author Joseph White
 * @version 1.04
 */
public class Method1 {
    	
	private static BufferedImage originalImage;
	private static File input;
	private static File output;
	private static int iterationCount;
	private static String iterations;
	private String[] options = {"Saturation", "Brigtness", "Saturation and Brightness"};
	
	/**
	 * Saves the image to the desktop and opens said image in the default image viewer
	 * @param filename
	 * 
	 */
	public static void postResult (String filename) {
		
		output = new File(System.getProperty("user.home") + "/Desktop/" + filename + ".jpg");
		try {
			ImageIO.write(originalImage, "jpg", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Conversion complete!");
		Desktop desktop = Desktop.getDesktop();
		
        if (output.exists()) {
			try {
				desktop.open(output);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	/**
	 * Saturates a given image to boost the color through a number of iterations.
	 * @param image Image to saturate
	 * @param iterate Number of iterations to saturate image
	 */
	public static void saturateAndBrightenImage(File image, int iterate) {
		
		try {
			originalImage = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < iterate; i++) {
			for (int y = 0; y < originalImage.getHeight(); y++) {
				for (int x = 0; x < originalImage.getWidth(); x++) {
					Color pixelColor = new Color(originalImage.getRGB(x, y));
					int red = pixelColor.getRed();
					int blue = pixelColor.getBlue();
					int green = pixelColor.getGreen();
					float[] hsb = Color.RGBtoHSB(red, green, blue, null);
					float hue = hsb[0];
					float saturation = hsb[1];
					float brightness = hsb[2];
					
					/* Method 1:
					 * Failure: Blue Blasted after 1 iteration
					int rgb = Color.HSBtoRGB(hue, saturation, brightness);
					red = (rgb>>16)&0xFF;
					green = (rgb>>8)&0xFF;
					blue = rgb&0xFF;
					int realRGB = red | blue | green;
					originalImage.setRGB(x, y, realRGB);
					*/
					
					/* Method 2:
					 * Failure: Deep Fired after 1 iteration
					if (saturation < 100) {
						saturation++;
					}
					else {
						saturation = 100;
					}
					int rgb = Color.HSBtoRGB(hue, saturation, brightness);
					originalImage.setRGB(x, y, rgb);
					*/
					
					if (saturation < 1.0) {
						saturation = saturation + 0.01f;
					}
					else {
						saturation = 1.0f;
					}
					
					if (brightness < 1.0) {
						brightness = brightness + 0.01f;
					}
					else {
						brightness = 1.0f;
					}
					
					int rgb = Color.HSBtoRGB(hue, saturation, brightness);
					originalImage.setRGB(x, y, rgb);
				}
			}
			System.out.println("Iteration " + (i+1) + ": Complete");
		}
		System.out.println("Saturation complete.");
	}
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Please select an image file you would like to convert.");
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	input = chooser.getSelectedFile();
        	JOptionPane.showMessageDialog(null,("You chose to open this file: " + chooser.getSelectedFile().getName()));
        }
        String outputName = JOptionPane.showInputDialog("What will be the name of the new image?");
        boolean isInt = false;
        while(isInt == false) {
        	try {
        		iterations = JOptionPane.showInputDialog("How many iterations of saturation? Please only type positive whole numbers.");
        		iterationCount = Integer.parseInt(iterations);
        		if (iterationCount > 0) {
        			isInt = true;
        		}
        	} catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, "That's not a whole positive number! Try again!");
        	}
        }
        
        saturateAndBrightenImage(input, iterationCount);
        postResult(outputName);
    }
}