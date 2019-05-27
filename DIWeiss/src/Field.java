import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Field extends Interfaces {
	public String myName;
	public double myData;
	public String myUnit;
	public static int count = 0;

	public Field()	{
		myName = "Field " + count;
		count++;
		myData = 0.0;
		myUnit = "None";
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
		switch(x)	{
		case 'P':	print();
		break;
		case 'N':	setName(sc);
		break;
		case 'V':	setData(sc);
		break;
		case 'U':	setUnit(sc);
		break;
		case 'R':	return null;
		}
		return this;


	}


	public void print()
	{
		System.out.println("\t\tField: \t" + myName);
		System.out.println("\t\t\t" + myData);
		System.out.println("\t\t\t" + myUnit);

	}

	public void export(FileWriter[] newFile)	{
		try {
			newFile[0].write("F " + myName + "\nF " + myData+ "\nF " + myUnit + "\n");
		} catch (IOException e) {
			System.out.println("Disappointed...");
		}
	}

	public void setName(Scanner[] sc)	{
		prompt(1);
		String theName = sc[0].nextLine();
		myName = theName;
	}
	public void setName(String theName)
	{
		myName = theName;
	}

	public void setData(Scanner[] sc)	{
		prompt(0);
		myData = sc[0].nextDouble();
		sc[0].nextLine();
	}
	
	public void setData(Double theData)
	{
		myData = theData;
	}

	public void setUnit(Scanner[] sc)	{
		prompt(2);
		myUnit = sc[0].nextLine();
	}
	
	public void setUnit(String theUnit)
	{
		myUnit = theUnit;
	}

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
	
	public int importField(Scanner[] scanner) {
		String temp = scanner[0].nextLine();
		if(temp.charAt(0) != 'F')
		{
			return -1;
		}
		temp = temp.substring(2, temp.length());
		setName(temp);
		temp = scanner[0].nextLine();
		scanner[1].nextLine();
		if(temp.charAt(0) != 'F')
		{
			return -1;
		}
		temp = temp.substring(2, temp.length());
		double temp2 = Double.parseDouble(temp);
		setData(temp2);
		temp = scanner[0].nextLine();
		scanner[1].nextLine();
		if(temp.charAt(0) != 'F')
		{
			return -1;
		}
		temp = temp.substring(2,  temp.length());
		setUnit(temp);
		return 0;
	}
}
