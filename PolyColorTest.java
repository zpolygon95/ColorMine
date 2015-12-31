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
					case "list":
						System.out.println("List of current variables:");
						for (int i = 0; i < names.size(); i++)
						{
							System.out.println(names.get(i) + " = " + vars.get(i));
						}
						break;
					case "cie94":
					case "CIE94":
						System.out.println(parseCie94(uiArgs));
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
				switch (args[2].toLowerCase())
				{
					case "rgb":
						if (args.length >= 6)
						{
							for (int i = 0; i < names.size(); i++)
							{
								if (names.get(i).equals(args[1]))
								{
									vars.set(i, new PolyRGB(
													Double.parseDouble(args[3]),
													Double.parseDouble(args[4]),
													Double.parseDouble(args[5])));
									return true;
								}
							}
							vars.add(new PolyRGB(Double.parseDouble(args[3]),
								Double.parseDouble(args[4]),
								Double.parseDouble(args[5])));
							names.add(args[1]);
						}
						else return false;
						break;
					case "cmy":
						if (args.length >= 6)
						{
							for (int i = 0; i < names.size(); i++)
							{
								if (names.get(i).equals(args[1]))
								{
									vars.set(i, new PolyCmy(
													Double.parseDouble(args[3]),
													Double.parseDouble(args[4]),
													Double.parseDouble(args[5])));
									return true;
								}
							}
							vars.add(new PolyCmy(Double.parseDouble(args[3]),
								Double.parseDouble(args[4]),
								Double.parseDouble(args[5])));
							names.add(args[1]);
						}
						else return false;
						break;
					case "lab":
						if (args.length >= 6)
						{
							for (int i = 0; i < names.size(); i++)
							{
								if (names.get(i).equals(args[1]))
								{
									vars.set(i, new PolyLab(
													Double.parseDouble(args[3]),
													Double.parseDouble(args[4]),
													Double.parseDouble(args[5])));
									return true;
								}
							}
							vars.add(new PolyLab(Double.parseDouble(args[3]),
								Double.parseDouble(args[4]),
								Double.parseDouble(args[5])));
							names.add(args[1]);
						}
						else return false;
						break;
					case "xyz":
						if (args.length >= 6)
						{
							for (int i = 0; i < names.size(); i++)
							{
								if (names.get(i).equals(args[1]))
								{
									vars.set(i, new PolyXYZ(
													Double.parseDouble(args[3]),
													Double.parseDouble(args[4]),
													Double.parseDouble(args[5])));
									return true;
								}
							}
							vars.add(new PolyXYZ(Double.parseDouble(args[3]),
								Double.parseDouble(args[4]),
								Double.parseDouble(args[5])));
							names.add(args[1]);
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