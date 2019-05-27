import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Settings {
	public String email = "example123@website.com";
	public String firstName = "John Doe";
	
	public void Export()
	{
		try {
			FileWriter writer = new FileWriter("Settings.txt");
			writer.write(email + "\n" + firstName + "\n");
			writer.close();
		} catch (IOException e) {
			
			File newFile = new File("Settings.txt");
			try {
				boolean success = newFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				FileWriter writer = new FileWriter(newFile);
				writer.write(email + "\n" + firstName + "\n");
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void Import()
	{
		try {
			FileReader reader = new FileReader("Settings.txt");
			Scanner sc = new Scanner(reader);
			email = sc.nextLine();
			firstName = sc.nextLine();
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			System.out.println("Settings.txt was not found.");
		}
	}
}
