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

            /// <summary>
            /// CIE2000 Test data courtesy of Gaurav Sharma http://www.ece.rochester.edu/~gsharma/ciede2000/
            /// </summary>
            [TestMethod]
            public void ReturnsKnownValueForKnownColors()
            {
                // Todo, should be mocking!!
                var a = new Lab
                {
                    L = 50.0000,
                    A = 2.6772,
                    B = -79.7751
                };

                var b = new Lab
                {
                    L = 50.0000,
                    A = 0.0000,
                    B = -82.7485
                };

                ReturnsExpectedValueForKnownInput(2.0425, a, b);
            }

            /// <summary>
            /// CIE2000 Test data courtesy of Gaurav Sharma http://www.ece.rochester.edu/~gsharma/ciede2000/
            /// </summary>
            [TestMethod]
            public void ReturnsKnownValueForKnownValues()
            {
                // Todo, should be mocking!!
                var a = new Lab
                {
                    L = 2.0776,
                    A = 0.0795,
                    B = -1.1350
                };

                var b = new Lab
                {
                    L = 0.9033,
                    A = -0.0636,
                    B = -0.5514
                };

                ReturnsExpectedValueForKnownInput(0.9082, a, b);
            }
        }
    }
}
