public class PolyColorTest
{
	public static void main(String[] args)
	{
		try
		{

			if (args.length == 3)
			{
				int r = Integer.parseInt(args[0]);
				double rd = ((double) r);
				int g = Integer.parseInt(args[1]);
				double gd = ((double) g);
				int b = Integer.parseInt(args[2]);
				double bd = ((double) b);
				PolyRGB rgb = new PolyRGB(rd, gd, bd);
				PolyXYZ xyz = new PolyXYZ();
				PolyLab lab = new PolyLab();
				xyz.initialize(rgb);
				lab.initialize(rgb);
				System.out.println("Xyz [" + xyz.X + ", " + xyz.Y + ", " + xyz.Z + "]");
				System.out.println("Lab [" + lab.L + ", " + lab.A + ", " + lab.B + "]");
			}

		}
		catch (NumberFormatException ex)
		{
			System.out.println("An Error Ocurred!");
		}
	}
}