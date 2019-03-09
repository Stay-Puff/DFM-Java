import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Deep Fried Manipulation Method 4: RGB Color Shifter
 * 
 * @author Joseph White
 * @version 1.05
 */
public class RGBColorShifter {

	private static BufferedImage originalImage;
	private static File input;
	private static File output;
	private static String[] options = {"Red Only", "Blue Only", "Green Only", "Red and Blue", "Blue and Green", "Red and Green"};
	private static String option;

	/**
	 * Saves the image to the desktop and opens said image in the default image viewer
	 * @param filename
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
	 * Shifts the colors of an image to fit a selected pattern
	 * @param image Image to change
	 */
	public static void shiftRGB(File image) {
		try {
			originalImage = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
			for (int y = 0; y < originalImage.getHeight(); y++) {
				for (int x = 0; x < originalImage.getWidth(); x++) {
					Color pixelColor = new Color(originalImage.getRGB(x, y));
					int red = pixelColor.getRed();
					int blue = pixelColor.getBlue();
					int green = pixelColor.getGreen();

					switch (option) {
						case "Red Only":{
							blue = 0;
							green = 0;
						}
						case "Blue Only":{
							red = 0;
							green = 0;
						}
						case "Green Only":{
							red = 0;
							blue = 0;
						}
						case "Red and Blue":{
							green = 0;
						}
						case "Blue and Green":{
							red = 0;
						}
						case "Red and Green":{
							blue = 0;
						}
					}
					
					pixelColor = new Color(red, blue, green);
					originalImage.setRGB(x, y, pixelColor.getRGB());
				}
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

		JComboBox optionList = new JComboBox(options);
		option = (String) optionList.getSelectedItem();

		shiftRGB(input);
		postResult(outputName);
	}
}