using ColorMine.ColorSpaces;
using ColorMine.Sets;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;

namespace ColorMine.Test.Sets
{
    [TestClass]
    public class AllCounterCounterTest
    {
        [TestClass]
        public class CountColors
        {
            [TestMethod]
            public void CountSinglePixel()
            {
                var matrix = new Mock<IColorMatrix>();
                matrix.Setup(x => x.Width).Returns(1);
                matrix.Setup(x => x.Height).Returns(1);
                
                var color = new Mock<IColorSpace>();

                matrix.Setup(x => x.GetColor(0, 0)).Returns(color.Object);

                var target = new AllColorCounter();
                var result = target.GetColorSet(matrix.Object);
                Assert.AreEqual(1, result[color.Object]);
            }
        }
    }
}
