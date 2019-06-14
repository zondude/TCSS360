package Model;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class that is for the settings for the program itself.
 * @author Patrick Lauer, Joel Johnson
 *
 */
public class Settings {
	public String email = "example123@website.com";
	public String firstName = "John Doe";

	public Settings()	{

	}

	/**
	 * Method to print the options during testing.
	 */
	public void printOptions()
	{
		System.out.println("Would you like to:");
		System.out.println("[P]rint your current profile information.");
		System.out.println("[C]hange your profile name.");
		System.out.println("[E]dit your email address.");
		System.out.println("[D]elete your profile settings.");
		System.out.println("e[X]port your settings.");
		System.out.println("[I]mport your settings.");
		System.out.println("[R]eturn to Home View");
	}

	/**
	 * A method to get user inputs and do something with it.
	 * @param sc the scanner.
	 */
	public void input(Scanner[] sc) {
		char x = ' ';
		do {	
			printOptions();
			String temp = sc[0].nextLine();
			if (temp.length() < 1) {
				System.out.println("You entered nothing.");
			}	else	{
				temp = temp.toUpperCase();
				x = temp.charAt(0);

				switch(x)	{
				case 'P':	print();
				break;
				case 'C':	setName(sc);
				break;
				case 'E':	setEmail(sc);
				break;
				case 'D':	deleteProfile();
				break;
				case 'X':	Export();
				break;
				case 'I':	Import();
				break;
				}
			}	
		}	while (x != 'R');
	}

	/**
	 * Print method for the name and the email.
	 */
	public void print()
	{
		System.out.println("\t\tName: " + firstName);
		System.out.println("\t\tEmail: " + email);
	}

	/**
	 * Method to set the name.
	 * @param sc the scanner.
	 */
	public void setName(Scanner[] sc)	
	{
		System.out.print("Enter new name: ");
		String theName = sc[0].nextLine();
		firstName = theName;
	}

	/**
	 * Method to set the email
	 * @param sc the scanner.
	 */
	public void setEmail(Scanner[] sc) 	
	{
		boolean acceptable = false;
		do	{
			
		System.out.print("Enter new e-mail: ");
		String theEmail = sc[0].nextLine();
		if (theEmail.contains("@") && theEmail.contains(".com")) {
			email = theEmail;
			acceptable = true;
		}	else	{
			System.out.println("Please use a valid email address.");
		}
		}	while (!acceptable);
		
	}

	/**
	 * Method to delete a profile.
	 */
	public void deleteProfile() 
	{
		email = "example123@website.com";
		firstName = "John Doe";
		System.out.println("Profile deleted. Restored to: \n");
		print();
	}

	/**
	 * Method to export the file.
	 */
	public void Export()
	{
		try {
			FileWriter writer = new FileWriter("Settings.txt");
			writer.write(email + "\n" + firstName + "\n");
			writer.close();
		} catch (IOException e) {

			File newFile = new File("Settings.txt");
			try {
				newFile.createNewFile();
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
	
	/**
	 * Method to import the file.
	 */
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
			sc.close();
		} catch (FileNotFoundException e) {

			System.out.println("Settings.txt was not found.");
		}
	}
}
