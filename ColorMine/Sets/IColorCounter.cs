using System.Collections.Generic;
using ColorMine.ColorSpaces;

namespace ColorMine.Sets
{
    public interface IColorCounter
    {
        IDictionary<IColorSpace,int> GetColorSet(IColorMatrix matrix);
    }
}