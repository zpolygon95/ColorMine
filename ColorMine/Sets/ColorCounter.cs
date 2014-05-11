using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using ColorMine.ColorSpaces;

namespace ColorMine.Sets
{
    public abstract class ColorCounter : IColorCounter
    {
        protected ConcurrentDictionary<IColorSpace,int> Counts { get; set; }

        protected ColorCounter()
        {
            Counts = new ConcurrentDictionary<IColorSpace, int>();
        }

        protected int Validate(IColorMatrix matrix)
        {
            try
            {
                return checked(matrix.Height*matrix.Width);
            }
            catch (OverflowException ex)
            {
                var message = String.Format("Currently doesn't support matrix with more than {0} elements: Given {1}x{2}", int.MaxValue, matrix.Width, matrix.Height);
                ex.Data.Add("Additional Information", message);
                throw;
            }
        }

        protected void Count(IColorSpace color)
        {
            if (color == null) throw new ArgumentNullException("color");
            Counts.AddOrUpdate(color, 1, (k, v) => Counts[color] += 1);
        }

        protected abstract IDictionary<IColorSpace, int> CountColors(IColorMatrix matrix);

        public IDictionary<IColorSpace, int> GetColorSet(IColorMatrix matrix)
        {
            Validate(matrix);
            return CountColors(matrix);
        }
    }
}