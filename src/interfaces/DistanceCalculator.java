package interfaces;

/**
 * Implementing this interface allows an object to be able to calculate the
 * distance of the given coordinates of two points by any metric.
 */
public interface DistanceCalculator {
	
	/** Override this method to define a specific metric for the calculation of the distance. 
	 * @param coords1 node/point i: (xi, yi (,zi))
	 * @param coords2 node/point j: (xj, yj (,zj))
	 * @return the calculated distance between the two points.
	 */
	public int calculateDistance(double[] coords1, double[] coords2);
}
