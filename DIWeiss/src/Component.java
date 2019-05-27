import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Component extends Interfaces {

	public String myName = null;
	public List<Field> fields;
	private static int count = 0;

	public Component()	{
		myName = "Component " + count;
		fields = new ArrayList<Field>();
		count++;
	}

	@Override
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

	@Override
	public Interfaces input(Scanner sc[])
	{
		String temp = sc[0].nextLine();
		if (temp.length() < 1) {
			System.out.println("You entered nothing.");
			return this;
		}
		temp = temp.toUpperCase();
		char x = temp.charAt(0);
		if(temp.equals("R"))
		{
			return null;
		}
		if(temp.length() == 1)
		{
			if(x == 'P')
			{
				print();
			}
			else if(x == 'L')
			{
				list();
			}
			else if(x == 'C')
			{
				create();
			}
			else if(x == 'E')
			{
				return edit(sc);
			}
			else if(x == 'D')
			{
				delete(sc);
			}
			else if(x == 'S')
			{
				setName(sc);
			}
		}
		return this;
	}

	public void print()
	{
		System.out.println("\tComponent: " + myName);
		for(Field f : fields)
		{
			f.print();
		}
	}
	public void list()
	{
		int index = 0;
		for (Field f : fields)
		{
			System.out.println("\t"+index+": \t"+f.myName);
			index++;
		}
	}

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
	
	public Field create()
	{
		Field field = new Field();
		fields.add(field);
		list();
		return field;
	}
	
	public Interfaces edit(Scanner sc[])
	{
		int count = 0;
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
	
	public void setName(Scanner sc[]) {
		System.out.print("Enter component name: ");
		String theName = sc[0].nextLine();
		myName = theName;
	}
	
	public void setName(String theName) {
		myName = theName;
	}
	
	public int importComp(Scanner[] scanner, int[] gap)	{
		String bling = scanner[0].nextLine();
		if(gap[0] == 0)
		{
			scanner[1].nextLine();
		}
		else
		{
			gap[0]--;
		}
		if (bling.charAt(0) != 'C')	{
			System.out.println("Not a valid project file.");
			return -1;
		}
		else
		{	
			String wing = bling.substring(2, bling.length());
			setName(wing);
			boolean done = false;
			while (!done) {
				String test = null;
				if(scanner[1].hasNextLine())
				{
					test = scanner[1].nextLine();
					gap[0]++;
				}
				if(test == null || test.charAt(0) != 'F')
				{
					done = true;
				}
				else
				{
					Field field = create();
					if(field.importField(scanner) == -1)
					{
						return -1;
					}
					gap[0]--;
				}
			}
		}
		return 0;
	}
	
	
}
