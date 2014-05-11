using System.Collections.Generic;
using System.Linq;
using ColorMine.ColorSpaces;
using ColorMine.ColorSpaces.Comparisons;

namespace ColorMine.ColorSets
{
    /// <summary>
    /// Converts each individual color to the nearest color in the Palette, based on the ComparisonStrategy
    /// </summary>
    public class SingleColorPaletteColorCounter : ColorCounter
    {
        public IColorSpaceComparison ComparisonStrategy { get; set; }
        public IEnumerable<IColorSpace> Palette { get; set; }

        protected override IDictionary<IColorSpace, int> CountColors(IColorMatrix matrix)
        {
            var map = new Dictionary<IColorSpace, IColorSpace>();

            for (var y = 0; y < matrix.Height; y++)
            {
                for (var x = 0; x < matrix.Width; x++)
                {
                    var matrixColor = matrix.GetColor(x, y);
                    var mappedColor = map.ContainsKey(matrixColor) ? map[matrixColor] : GetClosestColor(matrixColor);
                    Count(mappedColor);
                }
            }
            return Counts;
        }

        private IColorSpace GetClosestColor(IColorSpace matrixColor)
        {
            // TODO Order matters...so which should go first???
            return Palette.OrderByDescending(x => ComparisonStrategy.Compare(x,matrixColor)).FirstOrDefault();
        }
    }
}
