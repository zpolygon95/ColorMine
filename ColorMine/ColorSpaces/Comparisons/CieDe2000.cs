using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ColorMine.ColorSpaces.Comparisons
{
    /// <summary>
    /// Implements the DE2000 method of delta-e: http://en.wikipedia.org/wiki/Color_difference#CIEDE2000
    /// </summary>
    public class CieDe2000 : IColorSpaceComparison
    {
        /// <summary>
        /// Calculates the DE2000 delta-e value: http://en.wikipedia.org/wiki/Color_difference#CIEDE2000
        /// </summary>
        public double Compare(IColorSpace colorA, IColorSpace colorB)
        {
            const double kl = 1.0;
            const double kc = 1.0;
            const double kh = 1.0;

            var lab1 = colorA.To<Lab>();
            var lab2 = colorB.To<Lab>();
            
            var lDistancePrime = (lab1.L + lab2.L)/2;
            var c1 = Math.Sqrt(Math.Pow(lab1.A,2) + Math.Pow(lab1.B,2));
            var c2 = Math.Sqrt(Math.Pow(lab2.A, 2) + Math.Pow(lab2.B, 2));
            var cDistance = (c1 + c2)/2;
            var g = (1 - Math.Sqrt(Math.Pow(cDistance, 7)/(Math.Pow(cDistance, 7) + Math.Pow(25, 7))))/2;
            var aPrime1 = lab1.A * (1 + g);
            var aPrime2 = lab2.A * (1 + g);
            var cPrime1 = Math.Sqrt(Math.Pow(aPrime1, 2) + Math.Pow(lab1.B, 2));
            var cPrime2 = Math.Sqrt(Math.Pow(aPrime2, 2) + Math.Pow(lab2.B, 2));
            var cPrimeDistance = (cPrime1 + cPrime2)/2;
            var tan1 = Math.Atan2(lab1.B, aPrime1); // TODO Right?
            var hPrime1 = tan1 >= 0
                ? tan1
                : tan1 + 360;
            var tan2 = Math.Atan2(lab2.B, aPrime2);
            var hPrime2 = tan2 >= 0
                ? tan2
                : tan2 + 360;
            var hDiff = Math.Abs(hPrime1 - hPrime2);
            double deltaHPrime;
            if (hDiff <= 180)
            {
                deltaHPrime = hPrime2 - hPrime1;
            } else if (hDiff > 180 && hPrime2 <= hPrime1)
            {
                deltaHPrime = hPrime2 - hPrime1 + 360;
            }
            else
            {
                deltaHPrime = hPrime2 - hPrime1 - 360;
            }
            var deltaLPrime = lab2.L - lab1.L;
            var deltaCPrime = cPrime2 - cPrime1;
            var deltaHPrime = 2*Math.Sqrt(cPrime1*cPrime2)*Math.Sin(deltaHPrime/2);

            var sl = 1 + (.015 * (l))
        }
    }
}
