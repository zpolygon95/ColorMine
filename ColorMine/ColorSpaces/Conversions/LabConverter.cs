using System;

namespace ColorMine.ColorSpaces.Conversions
{
    internal static class LabConverter
    {
        
        private const double RefX = 95.047;
        private const double RefY = 100.000;
        private const double RefZ = 108.883;

        internal static void ToColorSpace(IRgb color, ILab item)
        {
            var xyz = new Xyz();
            xyz.Initialize(color);

            var x = PivotXyz(xyz.X/RefX);
            var y = PivotXyz(xyz.Y/RefY);
            var z = PivotXyz(xyz.Z/RefZ);

            item.L = Math.Max(0,116*y - 16);
            item.A = 500*(x - y);
            item.B = 200*(y - z);
        }

        private const double Epsilon = 0.008856;
        private const double Kappa = 903.3;
        internal static IRgb ToColor(ILab item)
        {
            var y = (item.L + 16.0) / 116.0;
            var x = item.A / 500.0 + y;
            var z = y - item.B / 200.0;

            var xyz = new Xyz
                {
                    X = RefX * (Math.Pow(x, 3) > Epsilon ? Math.Pow(x, 3) : (x - 16.0 / 116.0) / 7.787),
                    Y = RefY * (item.L > (Kappa * Epsilon) ? Math.Pow(((item.L + 16.0) / 116.0), 3) : item.L / Kappa),
                    Z = RefZ * (Math.Pow(z, 3) > Epsilon ? Math.Pow(z, 3) : (z - 16.0 / 116.0) / 7.787)
                };

            return xyz.ToRgb();
        }

        private static double PivotXyz(double n)
        {
            return n > Epsilon ? CubicRoot(n) : (Kappa*n + 16)/116;
        }

        private static double CubicRoot(double n)
        {
            return Math.Pow(n, (1.0/3.0));
        }
    }
}