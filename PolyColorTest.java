//package pctest;

import io.github.zpolygon95.polycolormine.*;
import io.github.zpolygon95.polycolormine.colorspace.*;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.Scanner;

public class PolyColorTest
{
	public static String COM_LIST =
		"help -- Displays this menu\n" +
		"quit -- Synonym for exit\n" +
		"exit -- Stops the program\n";
	public static String CS_LIST =
		"RGB";
	public static ArrayList<PolyColorSpace> vars = new ArrayList<>();
	public static ArrayList<String> names = new ArrayList<>();

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		System.out.println("+--------------------------------------------------------------+");
		System.out.println("| PolyColorTest --- Test program for the PolyColorMine library |");
		System.out.println("| run `help` for a list of commands                            |");
		System.out.println("+--------------------------------------------------------------+");
		while (run)
		{
			System.out.print(">>> ");
			String ui = sc.nextLine();
			String[] uiArgs = ui.split(" ");
			if (uiArgs.length > 0)
			{
				switch (uiArgs[0])
				{
					case "set":
						if (parseSet(uiArgs))
							System.out.println("OK.");
						else
							System.out.println("Usage: set <name> <type> <values ...>");
						break;
					case "get":
						if (uiArgs.length > 1)
						{
							for (int i = 0; i < names.size(); i++)
							{
								if (names.get(i).equals(uiArgs[1]))
								{
									System.out.println(vars.get(i));
									break;
								}
							}
						}
						break;
					case "help":
						System.out.println("List of valid commands:\n" + COM_LIST);
						break;
					case "quit":
					case "exit":
						run = false;
						System.out.println("done.");
						break;
					default:
						System.out.println("Command `" + uiArgs[0] + "\' not recognized.");
						System.out.println("Type `help\' for a list of valid commands.");
				}
			}
		}
	}

	public static boolean parseSet(String[] args)
	{
		try
		{
			if (args.length > 3)
			{
				switch (args[2])
				{
					case "RGB":
					case "rgb":
						if (args.length >= 6)
						{
							vars.add(new PolyRGB(Double.parseDouble(args[3]),
								Double.parseDouble(args[4]),
								Double.parseDouble(args[5])));
							names.add(args[2]);
						}
						else return false;
						break;
					default:
						System.out.println("List of valid types:\n" + CS_LIST);
						return false;
				}
			}
			else return false;
		}
		catch(NumberFormatException ex)
		{
			return false;
		}
		return true;
	}
}