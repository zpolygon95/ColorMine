package io.github.zpolygon95.polycolormine.comparison;

import io.github.zpolygon95.polycolormine.PolyColorSpaceComparison;
import io.github.zpolygon95.polycolormine.PolyColorSpace;
import io.github.zpolygon95.polycolormine.colorspace.PolyLab;

public class PolyCie94Comparison implements PolyColorSpaceComparison
{
	private ApplicationConstants constants;

	public PolyCie94Comparison ()
	{
		constants = new ApplicationConstants(Application.GraphicArts);
	}

	public PolyCie94Comparison (Application application)
	{
		constants = new ApplicationConstants(application);
	}

	public double compare(PolyColorSpace a, PolyColorSpace b)
	{
		PolyLab labA = (PolyLab) a.convertTo(PolyLab.class);
		PolyLab labB = (PolyLab) b.convertTo(PolyLab.class);

		double deltaL = labA.L - labB.L;
		double deltaA = labA.A - labB.A;
		double deltaB = labA.B - labB.B;

		double c1 = Math.sqrt((labA.A * labA.A) + (labA.B * labA.B));
		double c2 = Math.sqrt((labB.A * labB.A) + (labB.B * labB.B));
		double deltaC = c1 - c2;

		double deltaH = ((deltaA * deltaA) + (deltaB * deltaB)) - (deltaC * deltaC);
		deltaH = (deltaH < 0) ? 0 : Math.sqrt(deltaH);

		final double sl = 1.0;
		final double kc = 1.0;
		final double kh = 1.0;

		double sc = 1.0 + (constants.K1 * c1);
		double sh = 1.0 + (constants.K2 * c1);

		double deltaLKlsl = deltaL / (constants.Kl * sl);
		double deltaCkcsc = deltaC / (kc * sc);
		double deltaHkhsh = deltaH / (kh * sh);
		double i = (deltaLKlsl * deltaLKlsl) + (deltaCkcsc * deltaCkcsc) + (deltaHkhsh * deltaHkhsh);
		return (i < 0) ? 0 : Math.sqrt(i);
	}

	public enum Application
	{
		GraphicArts,
		Textiles
	}

	public class ApplicationConstants
	{
		public double Kl, K1, K2;

		public ApplicationConstants (Application application)
		{
			switch (application)
			{
				case GraphicArts:
					Kl = 1.0;
					K1 = .045;
					K2 = .015;
					break;
				case Textiles:
					Kl = 2.0;
					K1 = .048;
					K2 = .014;
					break;
			}
		}
	}
}