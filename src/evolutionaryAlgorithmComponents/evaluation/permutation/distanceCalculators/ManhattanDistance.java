package evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators;

import interfaces.DistanceCalculator;

public class ManhattanDistance implements DistanceCalculator {

	public ManhattanDistance() {
		
	}
	
	/**
	 * Calculates the 2D or 3D Manhattan distance between the given coordinates
	 * of the two points (L_1-metric).
	 */
	@Override
	public int calculateDistance(double[] coords1, double[] coords2) {
		double res = 0;
		for (int i=0; i<coords1.length; i++)
			res += Math.abs(coords1[i] - coords2[i]);
		return Math.round((float)res);
	}

}
