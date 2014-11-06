import java.lang.Class;
import java.lang.IllegalArgumentException;

public abstract class PolyColorSpace
{
	public abstract void initialize(PolyRGB color);

	protected abstract PolyRGB toRGB();

	public double compare(PolyColorSpace compareToValue, PolyColorSpaceComparison comparer)
	{
		return comparer.compare(this, compareToValue);
	}

	public PolyColorSpace convertTo(Class<? extends PolyColorSpace> colorSpace) throws IllegalArgumentException
	{
		if (!PolyColorSpace.class.isAssignableFrom(colorSpace))
		{
			throw new IllegalArgumentException("Argument colorSpace must extend PolyColorSpace");
		}
		try
		{
			if (colorSpace.isInstance(this))
			{
				return (PolyColorSpace)this.clone();
			}
			PolyColorSpace newColorSpace = colorSpace.newInstance();
			newColorSpace.initialize(toRGB());
			return newColorSpace;
		}
		catch (CloneNotSupportedException | InstantiationException | IllegalAccessException ex)
		{
			return null;
		}
	}
}