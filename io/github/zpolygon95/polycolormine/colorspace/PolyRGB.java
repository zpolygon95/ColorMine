package io.github.zpolygon95.polycolormine.colorspace;

import io.github.zpolygon95.polycolormine.PolyColorSpace;

public class PolyRGB extends PolyColorSpace
{
	public double R, G, B;

	public PolyRGB()
	{
		R = 0;
		G = 0;
		B = 0;
	}

	public PolyRGB(double r, double g, double b)
	{
		R = r;
		G = g;
		B = b;
	}

	@Override
	public void initialize(PolyRGB color)
	{
		PolyRGBConverter.toColorSpace(color, this);
	}

	@Override
	protected PolyRGB toRGB()
	{
		return PolyRGBConverter.toColor(this);
	}

	private static class PolyRGBConverter
	{
		private static void toColorSpace(PolyRGB color, PolyRGB item)
		{
			item.R = color.R;
			item.G = color.G;
			item.B = color.B;
		}

		private static PolyRGB toColor(PolyRGB item)
		{
			return item;
		}
	}
}