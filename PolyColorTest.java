//package pctest;

import io.github.zpolygon95.polycolormine.*;
import io.github.zpolygon95.polycolormine.colorspace.*;
import io.github.zpolygon95.polycolormine.comparison.*;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.Scanner;

public class PolyColorTest
{
	public static String COM_LIST =
		"help  -- Displays this menu\n" +
		"      -- Type `help <command>\' for usage" +
		"quit  -- Synonym for exit\n" +
		"exit  -- Stops the program\n" +
		"set   -- creates / modifies a named variable\n" +
		"get   -- prints the value of a named variable\n" +
		"list  -- lists all named variables\n" +
		"cie94 -- performs a cie94 comparison on two named variables";
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
					case "list":
						System.out.println("List of current variables:");
						for (int i = 0; i < names.size(); i++)
						{
							System.out.println(names.get(i) + " = " + vars.get(i));
						}
						break;
					case "conv":
						if (parseConv(uiArgs))
							System.out.println("OK.");
						else
							System.out.println("Usage: conv <var> <type>");
						break;
					case "cie94":
					case "CIE94":
						System.out.println(parseCie94(uiArgs));
						break;
					case "help":
						if (uiArgs.length > 1)
						{
							switch (uiArgs[1].toLowerCase())
							{
								case "help":
									System.out.println("help [command]");
									System.out.println("-- lists all valid commands, or optionally " +
										"the invocation of a specific command.");
									break;
								case "exit":
									System.out.println("NYI");
									break;
								case "quit":
									System.out.println("NYI");
									break;
								case "set":
									System.out.println("NYI");
									break;
								case "get":
									System.out.println("NYI");
									break;
								case "list":
									System.out.println("NYI");
									break;
								case "cie94":
									System.out.println("NYI");
									break;
								default:
									System.out.println("Command `" + uiArgs[0] + "\' not recognized.");
									System.out.println("Type `help\' for a list of valid commands.");
									break;
							}
						}
						else
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

	public static void setVar(String name, PolyColorSpace value)
	{
		for (int i = 0; i < names.size(); i++)
		{
			if (names.get(i).equals(name))
			{
				vars.set(i, value);
				return;
			}
		}
		vars.add(value);
		names.add(name);
	}

	public static PolyColorSpace parseVar(String type, String... values)
	{
		try
		{
			if (values.length >= 3)
			{
				switch (type.toLowerCase())
				{
					case "rgb":
						return (PolyColorSpace)
							new PolyRGB(
							Double.parseDouble(values[0]),
							Double.parseDouble(values[1]),
							Double.parseDouble(values[2]));
					case "cmy":
						return (PolyColorSpace)
							new PolyCmy(
							Double.parseDouble(values[0]),
							Double.parseDouble(values[1]),
							Double.parseDouble(values[2]));
					case "lab":
						return (PolyColorSpace)
							new PolyLab(
							Double.parseDouble(values[0]),
							Double.parseDouble(values[1]),
							Double.parseDouble(values[2]));
					case "xyz":
						return (PolyColorSpace)
							new PolyXYZ(
							Double.parseDouble(values[0]),
							Double.parseDouble(values[1]),
							Double.parseDouble(values[2]));
					default:
						System.out.println("List of valid types:\n" + CS_LIST);
				}
			}
		}
		catch(NumberFormatException ex)
		{
			System.out.println("Error: Please supply only decimal quantities");
		}
		return null;
	}

	public static boolean parseSet(String[] args)
	{ // set name type val val val
		if (args.length == 6)
		{
			PolyColorSpace val;
			if ((val = parseVar(args[2], args[3], args[4], args[5])) != null)
			{
				setVar(args[1], val);
				return true;
			}
		}
		return false;
	}

	public static boolean parseConv(String[] args)
	{
		// if (args.length >= 3)
		// {

		// }
		return false;
	}

	public static String parseCie94(String[] args)
	{
		String ret = "";
		if (args.length >= 3)
		{
			PolyColorSpace a = null;
			PolyColorSpace b = null;
			for (int i = 0; i < names.size(); i++)
			{
				if (names.get(i).equals(args[1]))
					a = vars.get(i);
				if (names.get(i).equals(args[2]))
					b = vars.get(i);
			}
			if (a != null)
			{
				if (b != null)
				{
					double val = 0;
					if (args.length > 3)
					{
						if (args[3].toLowerCase().equals("textiles"))
						{
							ret += "Using Textiles Application\n";
							PolyCie94Comparison com =
								new PolyCie94Comparison(PolyCie94Comparison.Application.Textiles);
							val = com.compare(a, b);
						}
						else
						{
							ret += "Using GraphicArts Application\n";
							PolyCie94Comparison com =
								new PolyCie94Comparison(PolyCie94Comparison.Application.GraphicArts);
							val = com.compare(a, b);
						}
					}
					else
					{
						ret += "Using Default Application\n";
						PolyCie94Comparison com = new PolyCie94Comparison();
						val = com.compare(a, b);
					}
					return ret + "Cie94(" + a + ", " + b + ") = " + val;
				}
				else
					ret += "variable `" + args[2] + "\' not defined.\n";
			}
			else
				ret += "variable `" + args[1] + "\' not defined.\n";
		}
		ret += "Usage: cie94 <var a> <var b> [graphic | textiles]";
		return ret;
	}
}