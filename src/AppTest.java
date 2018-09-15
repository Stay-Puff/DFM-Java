import java.awt.Color;
import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class AppTest {
	public static void main(String[] args) {
		
		Color Black = Color.BLACK;
		Color Blue = Color.BLUE;
		Color LightBlue = Color.CYAN;
		int BLACKred = Black.getRed();
		int BLACKblue = Black.getBlue();
		int BLACKgreen = Black.getGreen();
		float[] BLACKhsb = Color.RGBtoHSB(BLACKred, BLACKgreen, BLACKblue, null);
		float hue = BLACKhsb[0];
		float saturation = BLACKhsb[1];
		float brightness = BLACKhsb[2];
		System.out.println(saturation);
		System.out.println(brightness);
		
		int BLUEred = Blue.getRed();
		int BLUEblue = Blue.getBlue();
		int BLUEgreen = Blue.getGreen();
		float[] BLUEhsb = Color.RGBtoHSB(BLUEred, BLUEgreen, BLUEblue, null);
		hue = BLUEhsb[0];
		saturation = BLUEhsb[1];
		brightness = BLUEhsb[2];
		System.out.println(saturation);
		System.out.println(brightness);
		
		int CYANred = LightBlue.getRed();
		int CYANblue = LightBlue.getBlue();
		int CYANgreen = LightBlue.getGreen();
		float[] CYANhsb = Color.RGBtoHSB(CYANred, CYANgreen, CYANblue, null);
		hue = CYANhsb[0];
		saturation = CYANhsb[1];
		brightness = CYANhsb[2];
		System.out.println(saturation);
		System.out.println(brightness);
		System.out.println(System.getProperty("user.home") + "/Desktop/");
		System.out.println(453/1000.0);
	}
}
