public class PolyXYZ extends PolyColorSpace
{
	public double X, Y, Z;

	public PolyXYZ()
	{
		X = 0;
		Y = 0;
		Z = 0;
	}

	public PolyXYZ(double x, double y, double z)
	{
		X = x;
		Y = y;
		Z = z;
	}

	@Override
	public void initialize(PolyRGB color)
	{
		PolyXYZConverter.toColorSpace(color, this);
	}

	@Override
	protected PolyRGB toRGB()
	{
		return PolyXYZConverter.toColor(this);
	}

	public static class PolyXYZConverter
	{
		// see ColorMine/ColorMine/Conversions/XyzConverter.cs
		public static PolyXYZ whiteReference;
		public static final double Epsilon = 0.008856; // Intent is 216/24389
		public static final double Kappa = 903.3; // Intent is 24389/27
		static
		{
			whiteReference = new PolyXYZ();
			whiteReference.X = 95.047;
            whiteReference.Y = 100.000;
            whiteReference.Z = 108.883;
		}

		public static void toColorSpace(PolyRGB color, PolyXYZ item)
		{
			double r = PivotRgb(color.R / 255.0);
			double g = PivotRgb(color.G / 255.0);
			double b = PivotRgb(color.B / 255.0);
			// Observer = 2°, Illuminant = D65
			item.X = r * 0.4124 + g * 0.3576 + b * 0.1805;
			item.Y = r * 0.2126 + g * 0.7152 + b * 0.0722;
			item.Z = r * 0.0193 + g * 0.1192 + b * 0.9505;
		}

		public static PolyRGB toColor(PolyXYZ item)
		{
			// Observer = 2°, Illuminant = D65
			double x = item.X / 100.0;
			double y = item.Y / 100.0;
			double z = item.Z / 100.0;

			double r = x * 3.2406 + y * -1.5372 + z * -0.4986;
			double g = x * -0.9689 + y * 1.8758 + z * 0.0415;
			double b = x * 0.0557 + y * -0.2040 + z * 1.0570;

			r = (r > 0.0031308) ? (1.055 * Math.pow(r, 1 / 2.4) - 0.055) : (12.92 * r);
			g = (g > 0.0031308) ? (1.055 * Math.pow(g, 1 / 2.4) - 0.055) : (12.92 * g);
			b = (b > 0.0031308) ? (1.055 * Math.pow(b, 1 / 2.4) - 0.055) : (12.92 * b);

			PolyRGB rgb = new PolyRGB(r, g, b);
			return rgb;
		}

		public static double toRGB(double n)
		{
			double result = 255.0 * n;
			if (result > 255.0) return 255.0;
			if (result < 0.0) return 0.0;
			return result;
		}

		public static double cubicRoot(double n)
		{
			return Math.pow(n, 1.0/3.0);
		}

		public static double PivotRgb(double n)
		{
			return (n > 0.04045 ? Math.pow((n + 0.055) / 1.055, 2.4) : n / 12.92) * 100.0;
		}
	}
}