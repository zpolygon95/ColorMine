using System.Collections.Generic;
using ColorMine.ColorSpaces;

namespace ColorMine.Sets
{
    /// <summary>
    /// Count up ALL colors in an matrix
    /// </summary>
    public class AllColorCounter : ColorCounter
    {
        protected override IDictionary<IColorSpace, int> CountColors(IColorMatrix matrix)
        {
            for(var y = 0; y < matrix.Height; y++)
            {
                for(var x = 0; x < matrix.Width; x++)
                {
                    Count(matrix.GetColor(x, y));
                }
            }
            return Counts;
        }
    }
}