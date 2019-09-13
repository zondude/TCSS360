import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project extends Interfaces {

	public static String myName = null;
	public static List<Component> components; 

	Project(String theName)
	{
		myName = theName;
		components = new ArrayList<Component>();
	}

	@Override
	public void printOptions()
	{
		System.out.println("Would you like to:");
		System.out.println("[P]rint the current project.");
		System.out.println("[L]ist components.");
		System.out.println("[C]reate a new Component.");
		System.out.println("[E]dit an existing component.");
		System.out.println("[D]elete an existing component.");
		System.out.println("[S]et project name.");
		System.out.println("e[X]port current project.");
		System.out.println("[I]mport project.");
		System.out.println("[EXIT] to close application.");
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
		if(temp.equals("EXIT"))
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
			else if(x == 'X')
			{
				export(sc);
			}
			else if(x == 'I')
			{
				Project proj = importProject(sc);
				if(temp != null)
				{
					return proj;
				}
			}
		}
		return this;
	}

	public void print()
	{
		System.out.println("Project Name: " + myName);
		for (Component c : components)
		{
			c.print();
		}
	}
	public void list()
	{
		int index = 0;
		for (Component c : components)
		{
			System.out.println(index + ": \t"+c.myName);
			index++;
		}
	}

	public Component create()
	{
		Component temp = new Component();
		components.add(temp);
		list();
		return temp;
	}

	public Interfaces edit(Scanner sc[])
	{
		Component thisComponent = null;
		while (thisComponent == null)	{
			list();
			System.out.println("Which component?");
			int compIndex= sc[0].nextInt();
			sc[0].nextLine();
			try {
				thisComponent = components.get(compIndex);

			} catch (IndexOutOfBoundsException e)	{
				thisComponent = null;
				System.out.println("Out of Bounds, sir.");
			}
		}
		return thisComponent;
	}

	public void delete(Scanner sc[])
	{
		Component thisComponent = null;
		while (thisComponent == null)	{
			list();
			System.out.println("Which component?");
			int compIndex= sc[0].nextInt();
			sc[0].nextLine();
			try {
				thisComponent = components.remove(compIndex);

			} catch (IndexOutOfBoundsException e)	{
				thisComponent = null;
				System.out.println("Cannot burn, sir.");
			}
		}
	}

	public void setName(Scanner sc[]) {
		System.out.print("Enter new name: ");
		String theName = sc[0].nextLine();
		myName = theName;
	}

	public void export	(Scanner sc[])
	{
		String userEntry = null;

		while (userEntry == null)
		{
			System.out.println("Name your file: ");
			userEntry = sc[0].nextLine();
			if(userEntry.length() > 4 && userEntry.substring(userEntry.length() - 4, userEntry.length()).contains(".txt")) {

			}
			else if (userEntry.length() <= 4 && !userEntry.contains("."))
			{
				userEntry = userEntry + ".txt";
			}
			else
			{
				userEntry = null;
			}
		}
		try {
			FileWriter[] newFile = new FileWriter[1];
			newFile[0] = new FileWriter(userEntry);
			newFile[0].write("P " +  myName + "\n");
			for (Component c : components) {
				c.export(newFile);
			}
			newFile[0].close();
		} catch (IOException e) {
			System.out.println("Something bad happened.");
		}
	}

	public Project importProject(Scanner[] sc)	{
		Project project = null;
		FileReader reader = null;
		FileReader reader2 = null;
		String userEntry = null;
		while(reader == null)	{
			System.out.print("Enter Import project name: ");
			userEntry = sc[0].nextLine();
			try {
				reader = new FileReader(userEntry);
				reader2 = new FileReader(userEntry);
			} catch (FileNotFoundException e) {
				reader = null;
				reader2 = null;
			}
		}
		int gap[] = new int[1];
		gap[0] = 0;
		Scanner[] scanner = new Scanner[2];
		scanner[0] = new Scanner(reader);
		scanner[1] = new Scanner(reader2);
		String bling = scanner[0].nextLine();
		scanner[1].nextLine();
		if (bling.charAt(0) != 'P')	{
			System.out.println("Not a valid project file.");
			return null;
		}
		else
		{	
			String wing = bling.substring(2, bling.length());
			project = new Project(wing);
			while (scanner[0].hasNextLine()) {
				Component component = project.create();
				if (component.importComp(scanner, gap) == -1)	{
					return null;
				}
			}
		}
		scanner[0].close();
		try {
			reader.close();
		} catch (IOException e) {
		}
		print();
		return project;
	}
}

