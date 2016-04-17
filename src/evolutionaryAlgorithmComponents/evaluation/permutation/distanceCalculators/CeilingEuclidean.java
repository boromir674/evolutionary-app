package evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators;

import interfaces.DistanceCalculator;

public class CeilingEuclidean implements DistanceCalculator {

	public CeilingEuclidean() {

	}

	/**
	 * Calculates the Euclidean distance between the given coordinates
	 * of the two points (L_2-metric) using the {@link #Math.ceil() ceiling} function.
	 */
	@Override
	public int calculateDistance(double[] coords1, double[] coords2) {
		double res = 0;
		for (int i=0; i<coords1.length; i++)
			res += Math.pow(coords1[i] - coords2[i], 2);
		return (int) Math.ceil((float)Math.sqrt(res));
	}

}
