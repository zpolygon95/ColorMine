using System;

namespace ColorMine.ColorSpaces.Comparisons
{
    /// <summary>
    /// Implements the Cie94 method of delta-e: http://en.wikipedia.org/wiki/Color_difference#CIE94
    /// </summary>
    public class Cie94Comparison : IColorSpaceComparison
    {
        /// <summary>
        /// Application type defines constants used in the Cie94 comparison
        /// </summary>
        public enum Application
        {
            GraphicArts,
            Textiles
        }

        /// <summary>
        /// Create new Cie94Comparison. Defaults to GraphicArts application type.
        /// </summary>
        public Cie94Comparison()
        {
            Constants = new ApplicationConstants(Application.GraphicArts);
        }

        private ApplicationConstants Constants { get; set; }
        /// <summary>
        /// Create new Cie94Comparison for specific application type.
        /// </summary>
        /// <param name="application"></param>
        public Cie94Comparison(Application application)
        {
            Constants = new ApplicationConstants(application);
        }

        /// <summary>
        /// Compare colors using the Cie94 algorithm. The first color (a) will be used as the reference color.
        /// </summary>
        /// <param name="a">Reference color</param>
        /// <param name="b">Comparison color</param>
        /// <returns></returns>
        public double Compare(IColorSpace a, IColorSpace b)
        {
            var lchA = a.To<Lch>();
            var lchB = b.To<Lch>();

            var deltaL = lchA.L - lchB.L;
            var deltaC = lchA.C - lchB.C;
            var deltaH = lchA.H - lchB.H;

            var cx = lchA.C;
            const double sl = 1.0;
            var sc = 1 + .045*cx;
            var sh = 1 + .015*cx;

            return Math.Sqrt(
                Math.Pow(deltaL/(Constants.Kl*sl), 2) +
                Math.Pow(deltaC/(Constants.Kc*sc), 2) +
                Math.Pow(deltaH/(Constants.Kh*sh), 2)
                );
        }

        private class ApplicationConstants
        {
            internal double Kl { get; private set; }
            internal double Kc { get; private set; }
            internal double Kh { get; private set; }

            public ApplicationConstants(Application application)
            {
                switch (application)
                {
                    case Application.GraphicArts:
                        Kl = 1.0;
                        Kc = .045;
                        Kh = .015;
                        break;
                    case Application.Textiles:
                        Kl = 2.0;
                        Kc = .048;
                        Kh = .014;
                        break;
                    default:
                        throw new ArgumentException("Application type not supported");
                }
            }
        }
    }
}
