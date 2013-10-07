using System;
using System.Windows.Media;
using ColorMine.ColorSpaces.Conversions.Utility;

namespace ColorMine.ColorSpaces.Conversions
{
    internal static class CmykConverter
    {
        internal static void ToColorSpace(IRgb color, ICmyk item)
        {
            var cmy = new Cmy();
            cmy.Initialize(color);

            var k = 1.0;
            if (cmy.C < k) k = cmy.C;
            if (cmy.M < k) k = cmy.M;
            if (cmy.Y < k) k = cmy.Y;
            item.K = k;

            if (k.BasicallyEqualTo(1))
            {
                item.C = 0;
                item.M = 0;
                item.Y = 0;
            }
            else
            {
                item.C = (cmy.C - k) / (1 - k);
                item.M = (cmy.M - k) / (1 - k);
                item.Y = (cmy.Y - k) / (1 - k);
            }
        }

        internal static void ToColorSpace(IRgb color, ICmyk item, Uri profile)
        {
            var cmyk = WindowsColorSystem.TranslateColor(profile, color);
            item.C = cmyk.C;
            item.M = cmyk.M;
            item.Y = cmyk.Y;
            item.K = cmyk.K;
        }

        internal static IRgb ToColor(ICmyk item)
        {
            var cmy = new Cmy
                {
                    C = (item.C*(1 - item.K) + item.K),
                    M = (item.M*(1 - item.K) + item.K),
                    Y = (item.Y*(1 - item.K) + item.K)
                };

            return cmy.ToRgb();
        }

        internal static IRgb ToColor(ICmyk item, Uri profile)
        {
            var points = new[] { (float)item.C, (float)item.M, (float)item.Y, (float)item.K };
            var color = Color.FromValues(points, profile);
            return new Rgb
                {
                    R = color.R,
                    G = color.G,
                    B = color.B
                };
        }
    }
}