import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.Stack;

public class Home {
	static Scanner[] sc = new Scanner[1];
	
	static void pause(long time)
	{
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void Error()
	{
		
	}

	public static void main(String[] args) {
		System.out.print("Booting Up DIWeiss");
		sc[0] = new Scanner(System.in);
		for(int i = 0; i <= 2; i++)
		{
			//pause(1);
			System.out.print(".");
		}
		System.out.println();
		//pause(1);
		System.out.println("Creating Project...");
		//pause(1);
		System.out.print("Please enter a project name : ");
		String projectName = sc[0].nextLine();
		System.out.println("ProjectName : " + projectName);
		Project myProject = new Project(projectName);
		Stack<Interfaces> interfaceStack = new Stack<Interfaces>();
		Interfaces current = myProject;

		boolean done = false;
		//States:
		//0 - Project View
		//1 - Component View
		//2 - Field View
		//3 - Exit
		while(!done)
		{
			current.printOptions();
			Interfaces temp = current.input(sc);
			if(temp == null)
			{
				if(current == myProject)
				{
					done = true;
				}
				else
				{
					current = interfaceStack.pop();
				}
			}
			else if(current != temp)
			{
				interfaceStack.push(current);
				current = temp;
			}
			//Repeat
		}
	}

}
