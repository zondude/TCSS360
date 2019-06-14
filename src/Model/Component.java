package Model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A component class for the components for the project.
 * @author Abraham Lee, Jonathan Kim, Joel Johnson, Patrick Lauer
 *
 */
public class Component implements AppScheme {
	/** Initializing a name object. */
	public String myName;
	/** Initializing a list field object. */
	public List<Field> fields;
//	private static int count = 0;
	
	/**
	 * Default constructor.
	 */
	public Component() {
		this(new Scanner(""));
	}

	/**
	 * Constructor for the component.
	 * @param sc the scanner.
	 */
	public Component(Scanner sc)	{
		myName = "";
		fields = new ArrayList<Field>();
		importComp(sc);
//		count++;
	}
	
	/**
	 * Method to get the name of the component.
	 * @return the name of the component.
	 */
	public String getName() {
		return myName;
	}
	
	/**
	 * Method to get the list field.
	 * @return the list of fields.
	 */
	public List<Field> getFields() {
		return fields;
	}
	

	/** DEPRICATED
	 * Method to test the components.
	 */
	public void printOptions()
	{
		System.out.println("Currently Editing Component: " + myName);
		System.out.println("[P]rint the component.");
		System.out.println("[L]ist fields.");
		System.out.println("[C]reate a field.");
		System.out.println("[E]dit an existing field.");
		System.out.println("[D]elete an existing field.");
		System.out.println("[S]et component name.");
		System.out.println("[R]eturn to Project view.");
	}

//	public AppScheme input(Scanner sc[])
//	{
//		String temp = sc[0].nextLine();
//		if (temp.length() < 1) {
//			System.out.println("You entered nothing.");
//			return this;
//		}
//		temp = temp.toUpperCase();
//		char x = temp.charAt(0);
//		if(temp.equals("R"))
//		{
//			return null;
//		}
//		if(temp.length() == 1)
//		{
//			if(x == 'P')
//			{
//				print();
//			}
//			else if(x == 'L')
//			{
//				list();
//			}
//			else if(x == 'C')
//			{
//				create();
//			}
//			else if(x == 'E')
//			{
//				return edit(sc);
//			}
//			else if(x == 'D')
//			{
//				delete(sc);
//			}
//			else if(x == 'S')
//			{
//				setName(sc);
//			}
//		}
//		return this;
//	}

	/** DEPRICATED
	 * Method to print.
	 */
	public void print()
	{
		System.out.println("\tComponent: " + myName);
		for(Field f : fields)
		{
			f.print();
		}
	}
	
	/** DEPRICATED
	 * Method to list the components.
	 */
	public void list()
	{
		int index = 0;
		for (Field f : fields)
		{
			System.out.println("\t"+index+": \t"+f.myName);
			index++;
		}
	}

	/** DEPRICATED
	 * Method to export.
	 * @param newFile the file.
	 */
	public void export(FileWriter[] newFile)	{
		try {
			newFile[0].write("C " + myName + "\n");
			for (Field f : fields) {
				f.export(newFile);
			}
		} catch (IOException e) {
			System.out.println("Disappointed...");
		}
	}
	
//	public Field create()
//	{
//		Field field = new Field();
//		fields.add(field);
//		list();
//		return field;
//	}
	
	/** DEPRICATED
	 * Method that asks which field to edit.
	 * @param sc the scanner.
	 * @return the AppScheme.
	 */
	public AppScheme edit(Scanner sc[])
	{
//		int count = 0;
		Field thisField = null;
		while (thisField == null)	{
			list();
			System.out.println("Which field?");
			int fieldIndex= sc[0].nextInt();
			sc[0].nextLine();
			try {
				thisField = fields.get(fieldIndex);

			} catch (IndexOutOfBoundsException e)	{
				thisField = null;
				System.out.println("Out of Bounds, sir.");
			}
		}
		return thisField;
	}

	/** DEPRICATED
	 * Delete a component method.
	 * @param sc the scanner.
	 */
	public void delete(Scanner sc[])
	{
		Field thisField = null;
		while (thisField == null)	{
			list();
			System.out.println("Which field?");
			int fieldIndex= sc[0].nextInt();
			sc[0].nextLine();
			try {
				thisField = fields.remove(fieldIndex);

			} catch (IndexOutOfBoundsException e)	{
				thisField = null;
				System.out.println("Cannot burn, sir.");
			}
		}
	}
	
	/**	DEPRICATED
	 * Method to set the name.
	 * @param sc the Scanner.
	 */
	public void setName(Scanner sc[]) {
		System.out.print("Enter new name: ");
		String theName = sc[0].nextLine();
		myName = theName;
	}
	
	/** DEPRICATED
	 * Method to set the name.
	 * @param theName the name of the component.
	 */
	public void setName(String theName) {
		myName = theName;
	}
	
	/**
	 * Method to import the component.
	 * @param sc the scanner.
	 */
	public void importComp(Scanner sc) {
		if (sc.hasNextLine()) {
			myName = sc.nextLine();
			Scanner fieldSc = sc.useDelimiter("F ");
			while (fieldSc.hasNext()) {
				String test = fieldSc.next();
				fields.add(new Field(new Scanner(test)));
			}
		}
	}
	
//	public int importComp(Scanner[] scanner, int[] gap)	{
//		String bling = scanner[0].nextLine();
//		if(gap[0] == 0)
//		{
//			scanner[1].nextLine();
//		}
//		else
//		{
//			gap[0]--;
//		}
//		if (bling.charAt(0) != 'C')	{
//			System.out.println("Not a valid project file.");
//			return -1;
//		}
//		else
//		{	
//			String wing = bling.substring(2, bling.length());
//			setName(wing);
//			boolean done = false;
//			while (!done) {
//				String test = null;
//				if(scanner[1].hasNextLine())
//				{
//					test = scanner[1].nextLine();
//					gap[0]++;
//				}
//				if(test == null || test.charAt(0) != 'F')
//				{
//					done = true;
//				}
//				else
//				{
//					Field field = create();
//					if(field.importField(scanner) == -1)
//					{
//						return -1;
//					}
//					gap[0]--;
//				}
//			}
//		}
//		return 0;
//	}
	
	
}
