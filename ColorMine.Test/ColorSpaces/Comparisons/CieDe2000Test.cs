using ColorMine.ColorSpaces;
using ColorMine.ColorSpaces.Comparisons;
using ColorMine.ColorSpaces.Conversions.Utility;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace ColorMine.Test.ColorSpaces.Comparisons
{
    public class CieDe2000Test
    {
        [TestClass]
        public class Compare
        {
            private void ReturnsExpectedValueForKnownInput(double expectedValue, IColorSpace a, IColorSpace b)
            {
                var target = new CieDe2000Comparison();
                var actualValue = a.Compare(b, target);
                Assert.IsTrue(expectedValue.BasicallyEqualTo(actualValue), expectedValue + " != " + actualValue);
            }

            [TestMethod]
            public void ReturnsKnownValueForRedAndMaroon2()
            {
                // Todo, should be mocking!!
                var a = new Lab
                {
                    L = 24.8290,
                    A = 60.0930,
                    B = 38.1800
                };

                var b = new Lab
                {
                    L = 53.2300,
                    A = 80.1090,
                    B = 67.2200
                };

                ReturnsExpectedValueForKnownInput(26.1061, a, b);
            }
        }
    }
}
