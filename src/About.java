import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;

/**
 * 
 * @author Abraham, Joel, Jonathan, Patrick
 * 
 * Purpose is to open an About screen with information
 * about our team and version number.
 *
 */
public class About extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    About frame = new About();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     * @throws FileNotFoundException 
     */
    public About() throws FileNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLayeredPane layeredPane = new JLayeredPane();
        contentPane.add(layeredPane, BorderLayout.CENTER);

        JLabel lblNewLabel = new JLabel("Abraham, Joel, Jonathan, Patrick");
        lblNewLabel.setBounds(109, 90, 219, 16);
        layeredPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Ver: " + getVersion().toString());
        lblNewLabel_1.setBounds(180, 117, 61, 16);
        layeredPane.add(lblNewLabel_1);
    }

    /**
     * Gets version number from txt file.
     * @return version number
     * @throws FileNotFoundException
     */
    public Double getVersion() throws FileNotFoundException {
        File file = new File("txt/version.txt");
        Scanner sc = new Scanner(file);
        double version = Double.parseDouble(sc.nextLine());
        return version;
    }
}