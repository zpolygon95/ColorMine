using ColorMine.ColorSpaces;
using ColorMine.ColorSpaces.Comparisons;
using ColorMine.ColorSpaces.Conversions.Utility;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace ColorMine.Test.ColorSpaces.Comparisons
{
    public class Cie94ComparisonTest
    {
        [TestClass]
        public class Compare
        {
            private void ReturnsExpectedValueForKnownInput(double expectedValue, Cie94Comparison.Application application, IColorSpace a, IColorSpace b)
            {
                var target = new Cie94Comparison(application);
                var actualValue = a.Compare(b, target);
                Assert.IsTrue(expectedValue.BasicallyEqualTo(actualValue), expectedValue + " != " + actualValue);
            }

            [TestMethod]
            public void ReturnsKnownValueForGraphicArtsPinks()
            {
                // Todo, should be mocking!!
                var a = new Lab
                    {
                        L = 70.1,
                        A = 53,
                        B = -3.2
                    };

                // Todo, should be mocking!!
                var b = new Lab
                {
                    L = 67.4,
                    A = 47.7,
                    B = -5.34
                };

                ReturnsExpectedValueForKnownInput(3.408967, Cie94Comparison.Application.GraphicArts, a, b);
            }
        }
    }
}
