import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Deep Fried Manipulation Method 3: Contrast Boost
 * 
 * @author Joseph White
 * @version 1.05
 */
public class ContrastBoost extends JFrame{
	
	private static BufferedImage originalImage;
	private static BufferedImage finalImage;
	private static String filename;
	private static File inputFile;
	private static File outputFile;  
	private static float scaleFactor = 2.6f; //change scaleFactor to change contrast        
	/*keep the value scaleFactor = 1.0f; as for a normal image*/
	private static RescaleOp rescale;
	
	/**
	 * Contrasts the image
	 */
	private static void contrastChange(){
		rescale = new RescaleOp(scaleFactor,20.0f, null);
		finalImage = rescale.filter(originalImage, finalImage);    
	}
	
	
	/**
	 * Saves the image to the desktop and opens said image in the default image viewer
	 * @param filename
	 * 
	 */
	public static void postResult (String filename) {
		outputFile = new File(System.getProperty("user.home") + "/Desktop/" + filename + ".jpg");
		try {
			ImageIO.write(finalImage, "jpg", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Conversion complete!");
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
        	JOptionPane.showMessageDialog(null,("You chose to open this file: " + chooser.getSelectedFile().getName()));
        }
        filename = JOptionPane.showInputDialog("What will be the name of the new image?");
        
        try {           
			originalImage = ImageIO.read(inputFile); // create a buffered image
		} catch (IOException ex) {
			Logger.getLogger(ContrastBoost.class.getName()).log(Level.SEVERE, null, ex);
		}
		contrastChange();
		postResult(filename);
	}
}