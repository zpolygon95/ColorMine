namespace ColorMine.ColorSpaces.Conversions
{
    /// <summary>
    /// HSB is another name for HSL
    /// </summary>
    internal static class HsbConverter
    {
        internal static void ToColorSpace(IRgb color, IHsb item)
        {
            var hsl = new Hsl();
            HslConverter.ToColorSpace(color, hsl);

            item.H = hsl.H;
            item.S = hsl.S;
            item.B = hsl.L;
        }

        internal static IRgb ToColor(IHsb item)
        {
            var hsl = new Hsl
                {
                    H = item.H,
                    S = item.S,
                    L = item.B
                };
            return HslConverter.ToColor(hsl);
        }
    }
}