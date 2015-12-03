package io.github.zpolygon95.polycolormine.colorspace;

import io.github.zpolygon95.polycolormine.PolyColorSpace;

public class PolyLab extends PolyColorSpace
{
	public double L, A, B;

	public PolyLab()
	{
		L = 0;
		A = 0;
		B = 0;
	}

	public PolyLab(double l, double a, double b)
	{
		L = l;
		A = a;
		B = b;
	}

	@Override
	public void initialize(PolyRGB color)
	{
		PolyLabConverter.toColorSpace(color, this);
	}

	@Override
	protected PolyRGB toRGB()
	{
		return PolyLabConverter.toColor(this);
	}

	public static class PolyLabConverter
	{
		public static void toColorSpace(PolyRGB color, PolyLab item)
		{
			PolyXYZ xyz = new PolyXYZ();
			xyz.initialize(color);

			PolyXYZ white = PolyXYZ.PolyXYZConverter.whiteReference;
			double x = pivotXYZ(xyz.X / white.X);
			double y = pivotXYZ(xyz.Y / white.Y);
			double z = pivotXYZ(xyz.Z / white.Z);

			item.L = Math.max(0, 116 * y - 16);
			item.A = 500 * (x - y);
			item.B = 200 * (y - z);
		}

		public static PolyRGB toColor(PolyLab item)
		{
			double y = (item.L + 16.0) / 116.0;
			double x = item.A / 500.0 + y;
			double z = y - item.B / 200.0;

			PolyXYZ white = PolyXYZ.PolyXYZConverter.whiteReference;
			double x3 = x * x * x;
			double z3 = z * z * z;

			PolyXYZ xyz = new PolyXYZ(
				white.X * (x3 > PolyXYZ.PolyXYZConverter.Epsilon ? x3 : (x - 16.0 / 116.0) / 7.787),
				white.Y * (item.L > (PolyXYZ.PolyXYZConverter.Kappa * PolyXYZ.PolyXYZConverter.Epsilon) ? Math.pow(((item.L + 16.0) / 116.0), 3) : item.L / PolyXYZ.PolyXYZConverter.Kappa),
				white.Z * (z3 > PolyXYZ.PolyXYZConverter.Epsilon ? z3 : (z - 16.0 / 116.0) / 7.787));
			return xyz.toRGB();
		}

		public static double cubicRoot(double n)
		{
			return Math.pow(n, 1.0/3.0);
		}

		public static double pivotXYZ(double n)
		{
			return n > PolyXYZ.PolyXYZConverter.Epsilon ? cubicRoot(n) : (PolyXYZ.PolyXYZConverter.Kappa * n + 16) / 116;
		}
	}
}