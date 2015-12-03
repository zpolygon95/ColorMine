package io.github.zpolygon95.polycolormine.colorspace;

import io.github.zpolygon95.polycolormine.PolyColorSpace;

public class PolyCmy extends PolyColorSpace
{
	public double C, M, Y;

	public PolyCmy()
	{
		C = 0;
		M = 0;
		Y = 0;
	}

	public PolyCmy(double c, double m, double y)
	{
		C = c;
		M = m;
		Y = y;
	}

	@Override
	public void initialize(PolyRGB color)
	{
		PolyCmyConverter.toColorSpace(color, this);
	}

	@Override
	protected PolyRGB toRGB()
	{
		return PolyCmyConverter.toColor(this);
	}

	public static class PolyCmyConverter
	{
		public static void toColorSpace(PolyRGB color, PolyCmy item)
		{
			item.C = 1 - color.R / 255.0;
			item.M = 1 - color.G / 255.0;
			item.Y = 1 - color.B / 255.0;
		}

		public static PolyRGB toColor(PolyCmy item)
		{
			return new PolyRGB((1 - item.C) * 255.0,
				(1 - item.M) * 255.0,
				(1 - item.Y) * 255.0);
		}
	}
}