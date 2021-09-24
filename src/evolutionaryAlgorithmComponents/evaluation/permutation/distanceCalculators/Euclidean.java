package evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators;

import interfaces.DistanceCalculator;

public class Euclidean implements DistanceCalculator {

	public Euclidean() {

	}

	/**
	 * Calculates the 2D or 3D Euclidean distance between the given coordinates
	 * of the two points (L_2-metric).
	 */
	@Override
	public int calculateDistance(double[] coords1, double[] coords2) {
		double res = 0;
		for (int i=0; i<coords1.length; i++)
			res += Math.pow(coords1[i] - coords2[i], 2);
		return Math.round((float)Math.sqrt(res));
	}

}
