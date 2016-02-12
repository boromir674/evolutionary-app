package interfaces;

public interface MathFunction {
	/**
	 * A mathematical function f: R^n -> R. 
	 * @param values is a n-dimensional vector.
	 * @return f(x)
	 */
	public double f(double[] values);
	/**
	 * A boolean method that determines if the mathematical function has a minimum or maximum.
	 * @return true if the function has a minimum,
	 * false if the function has a maximum
	 */
	public boolean hasMin();
}
