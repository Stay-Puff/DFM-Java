import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Method2 {
	
	private static BufferedImage originalImage;
	private static BufferedImage outputImage;
	private static String filename;
	private static File inputFile;
	private static File outputFile;
	private static int iterationTicks;
	
	/**
	 * 
	 * @param iterations
	 */
	public static void moreJPEG(int iterations) {
		try {
			originalImage = ImageIO.read(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
		jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegParams.setCompressionQuality(1f - (float)(iterations/1000.0));
		final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
		outputFile = new File(System.getProperty("user.home") + "/Desktop/" + filename + ".jpg");
		// specifies where the jpg image has to be written
		try {
			writer.setOutput(new FileImageOutputStream(outputFile));
			// writes the file with given compression level 
			// from your JPEGImageWriteParam instance
			writer.write(null, new IIOImage(originalImage, null, null), jpegParams);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Desktop desktop = Desktop.getDesktop();
		
        if (outputFile.exists()) {
			try {
				desktop.open(outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Please select an image file you would like to convert.");
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	inputFile = chooser.getSelectedFile();
        	//String imgPath = imgInput.getAbsolutePath();
        	JOptionPane.showMessageDialog(null,("You chose to open this file: " + chooser.getSelectedFile().getName()));
        }
        filename = JOptionPane.showInputDialog("What will be the name of the new image?");
        boolean isInt = false;
        while(isInt == false) {
        	try {
        		String i = JOptionPane.showInputDialog("How many iterations of JPEG? Please choose a positive integer between 0 and 1000.");
        		int iterationCount = Integer.parseInt(i);
        		if (iterationCount >= 0 && iterationCount <= 1000) {
        			iterationTicks =  iterationCount;
        			isInt = true;
        		}
        	} catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, "That's not a whole positive number! Try again!");
        	}
        }
		moreJPEG(iterationTicks);
	}
}
