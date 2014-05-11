using ColorMine.ColorSpaces;

namespace ColorMine.Sets
{
    public interface IColorMatrix
    {
        int Height { get; }
        int Width { get; }
        IColorSpace GetColor(int x, int y);
    }
}