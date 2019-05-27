import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;  
public class SwingTest {

	//public static void main(String[] args) {
	public static void stain(String[] args) {
		ImageIcon trashIcon = new ImageIcon("trashcan.png");
		Image img = trashIcon.getImage();
		Image newimg = img.getScaledInstance( 40,  40, java.awt.Image.SCALE_SMOOTH);
		trashIcon = new ImageIcon( newimg );
		JFrame f = new JFrame();
		
		JButton b = new JButton();
		b.setIcon(trashIcon);
		b.setBounds(130,100,40,40);
		
		f.add(b);
		f.setSize(400,500);
		f.setLayout(null);
		f.setVisible(true);
	}

}
