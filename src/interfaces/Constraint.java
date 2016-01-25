package interfaces;

public interface Constraint {
	/**
	 * This method returns the maximal domain from which the input x takes values
	 * eg -5.12 < x < 5.12
	 * @return the lower and upper bound that define the domain
	 */
	public double[] getDomain();
	/**
	 * This method is used to determine if the specified values form a domain that is a 
	 * @param lowerBound
	 * @param upperBound
	 * @return
	 */
	public boolean satisfiesDomain(double lowerBound, double upperBound);
}
