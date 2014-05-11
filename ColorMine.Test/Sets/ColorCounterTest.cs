using System;
using System.Collections.Generic;
using ColorMine.ColorSpaces;
using ColorMine.Sets;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;

namespace ColorMine.Test.Sets
{
    [TestClass]
    public class ColorCounterTest
    {
        protected class ColorCounterStub : ColorCounter
        {
            // Just exposes the protected method for testing, blech!
            public IDictionary<IColorSpace, int> CountColor(IColorSpace color)
            {
                Count(color);
                return Counts;
            }

            protected override IDictionary<IColorSpace, int> CountColors(IColorMatrix matrix)
            {
                return null;
            }
        }
        
        [TestClass]
        public class Validate
        {
            [TestMethod]
            [ExpectedException(typeof(OverflowException))]
            public void LargeMatrixThrowsException()
            {
                TestSize(int.MaxValue / 2 + 1, 2);
            }

            [TestMethod]
            public void AdditionalInformationSetOnException()
            {
                try
                {
                    TestSize(int.MaxValue/2 + 1, 2);
                }
                catch (OverflowException ex)
                {
                    Assert.IsTrue(ex.Data.Contains("Additional Information"));
                }
            }

            [TestMethod]
            public void MaxSizeIsValid()
            {
                TestSize(int.MaxValue, 1);
            }

            private static void TestSize(int width, int height)
            {
                var matrix = new Mock<IColorMatrix>();
                matrix.Setup(x => x.Width).Returns(width);
                matrix.Setup(x => x.Height).Returns(height);

                var target = new ColorCounterStub();
                target.GetColorSet(matrix.Object);
            }

        }
    
        [TestClass]
        public class Count
        {
            [TestMethod]
            public void CountStartsAtOne()
            {
                var color = new Mock<IColorSpace>();
                
                var target = new ColorCounterStub();
                var result = target.CountColor(color.Object);
                Assert.AreEqual(1, result[color.Object]);
            }

            [TestMethod]
            public void CountContinuesForDuplicateColors()
            {
                var color = new Mock<IColorSpace>();

                var target = new ColorCounterStub();
                target.CountColor(color.Object);
                target.CountColor(color.Object);
                var result = target.CountColor(color.Object);
                Assert.AreEqual(3, result[color.Object]);
            }
        }
    }
}
