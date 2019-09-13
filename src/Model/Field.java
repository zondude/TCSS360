package Model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class that are the fields of the components.
 * @author Abraham Lee, Jonathan Kim, Joel Johnson, Patrick Lauer
 *
 */
public class Field implements AppScheme {
	/** Initializing a name object. */
	public String myName;
	/** Initializing a data object. */
	public double myData;
	/** Initializing a unit object. */
	public String myUnit;
//	public static int count = 0;

	/**
	 * Constructor for field.
	 */
	public Field() {
		this(new Scanner(""));
	}
	
	/**
	 * Constructor for field that takes in a scanner.
	 * @param sc the scanner.
	 */
	public Field(Scanner sc)	{
		myName = "";
		myData = 0.0;
		myUnit = "";
		importField(sc);
//		count++;
	}
	
	/**
	 * Method that gets the name.
	 * @return the name.
	 */
	public String getName() {
		return myName;
	}
	
	/**
	 * Method that gets the data.
	 * @return the data.
	 */
	public double getData() {
		return myData;
	}
	
	/**
	 * Method that returns the unit.
	 * @return the unit.
	 */
	public String getUnit() {
		return myUnit;
	}
	
	@Override
	public void printOptions()
	{
		System.out.println("Currently Editing Field: " + myName);
		System.out.println("[P]rint the Field.");
		System.out.println("change [N]ame");
		System.out.println("change [V]alue");
		System.out.println("change [U]nit");
		System.out.println("[R]eturn to Component View.");
	}

//	@Override
//	public AppScheme input(Scanner sc[])
//	{
//		String temp = sc[0].nextLine();
//		if (temp.length() < 1) {
//			System.out.println("You entered nothing.");
//			return this;
//		}
//		temp = temp.toUpperCase();
//		char x = temp.charAt(0);
//		switch(x)	{
//		case 'P':	print();
//		break;
//		case 'N':	setName(sc);
//		break;
//		case 'V':	setData(sc);
//		break;
//		case 'U':	setUnit(sc);
//		break;
//		case 'R':	return null;
//		}
//		return this;
//
//
//	}


	/**
	 * Method that prints the fields.
	 */
	public void print()
	{
		System.out.println("\t\tField: \t" + myName);
		System.out.println("\t\t\t" + myData);
		System.out.println("\t\t\t" + myUnit);

	}

	/**
	 * Method that exports the file.
	 * @param newFile the file.
	 */
	public void export(FileWriter[] newFile)	{
		try {
			newFile[0].write("F " + myName + "\nF " + myData+ "\nF " + myUnit + "\n");
		} catch (IOException e) {
			System.out.println("Disappointed...");
		}
	}

	/**
	 * Method that sets the name.
	 * @param sc the scanner.
	 */
	public void setName(Scanner[] sc)	{
		prompt(1);
		String theName = sc[0].nextLine();
		myName = theName;
	}
	/**
	 * Method that sets the name.
	 * @param theName the name.
	 */
	public void setName(String theName)
	{
		myName = theName;
	}

	/**
	 * Method that sets the data.
	 * @param sc the scanner.
	 */
	public void setData(Scanner[] sc)	{
		prompt(0);
		myData = sc[0].nextDouble();
		sc[0].nextLine();
	}
	
	/**
	 * Method that sets the data.
	 * @param theData the data.
	 */
	public void setData(Double theData)
	{
		myData = theData;
	}

	/**
	 * Method that sets the unit
	 * @param sc the scanner.
	 */
	public void setUnit(Scanner[] sc)	{
		prompt(2);
		myUnit = sc[0].nextLine();
	}
	
	/**
	 * Method that sets the unit.
	 * @param theUnit the unit of measurement.
	 */
	public void setUnit(String theUnit)
	{
		myUnit = theUnit;
	}

	/** DEPRICATED
	 * Method to pull up the prompt.
	 * @param request the request from the user.
	 */
	public void prompt(int request) {
		System.out.print("Enter new ");
		switch(request)	{
		case 0:
			System.out.println("value: ");
			break;
		case 1:
			System.out.print("name: ");
			break;
		case 2:
			System.out.println("unit: ");
		}
	}
	
	/** DEPRICATED
	 * Method to import the field.
	 * @param sc the scanner.
	 */
	public void importField(Scanner sc) {
		if (sc.hasNextLine()) {
			Scanner fieldSc = sc.useDelimiter(", ");
			myName = fieldSc.next();
			myData = Double.parseDouble(fieldSc.next());
			myUnit = fieldSc.next().replace("\n", "");
		}
	}
//		String temp = scanner[0].nextLine();
//		if(temp.charAt(0) != 'F')
//		{
//			return -1;
//		}
//		temp = temp.substring(2, temp.length());
//		setName(temp);
//		temp = scanner[0].nextLine();
//		scanner[1].nextLine();
//		if(temp.charAt(0) != 'F')
//		{
//			return -1;
//		}
//		temp = temp.substring(2, temp.length());
//		double temp2 = Double.parseDouble(temp);
//		setData(temp2);
//		temp = scanner[0].nextLine();
//		scanner[1].nextLine();
//		if(temp.charAt(0) != 'F')
//		{
//			return -1;
//		}
//		temp = temp.substring(2,  temp.length());
//		setUnit(temp);
//		return 0;
//	}
}
