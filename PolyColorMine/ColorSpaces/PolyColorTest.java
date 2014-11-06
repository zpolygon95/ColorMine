public class PolyColorTest
{
	public static void main(String[] args)
	{
		try
		{

			if (args.length == 6)
			{
				int r1 = Integer.parseInt(args[0]);
				double r1d = ((double) r1);
				int g1 = Integer.parseInt(args[1]);
				double g1d = ((double) g1);
				int b1 = Integer.parseInt(args[2]);
				double b1d = ((double) b1);
				int r2 = Integer.parseInt(args[3]);
				double r2d = ((double) r2);
				int g2 = Integer.parseInt(args[4]);
				double g2d = ((double) g2);
				int b2 = Integer.parseInt(args[5]);
				double b2d = ((double) b2);
				PolyRGB rgb1 = new PolyRGB(r1d, g1d, b1d);
				PolyRGB rgb2 = new PolyRGB(r2d, g2d, b2d);
				double delta = new PolyCie94Comparison().compare(rgb1, rgb2);
				System.out.println("CIE94(["+rgb1.R+","+rgb1.G+","+rgb1.B+"], ["+rgb1.R+","+rgb1.G+","+rgb1.B+"]) = " + delta);
			}
			else
			{
				System.out.println("Not enough arguments! (6)");
			}

		}
		catch (NumberFormatException ex)
		{
			System.out.println("An Error Ocurred!");
		}
	}
}