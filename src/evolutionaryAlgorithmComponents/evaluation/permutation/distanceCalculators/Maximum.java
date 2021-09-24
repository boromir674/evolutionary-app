package evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators;

import interfaces.DistanceCalculator;

public class Maximum implements DistanceCalculator {

	public Maximum() {
	
	}

	/**
	 * Calculates the 2D or 3D Maximum distance between the given coordinates
	 * of the two points (L_inf-metric.
	 */
	@Override
	public int calculateDistance(double[] coords1, double[] coords2) {
		int max = -1;
		int temp = 0;
		for (int i=0; i<coords1.length; i++)
			temp = Math.round((float)Math.abs(coords1[i] - coords2[i]));
		if (temp > max)
			max = temp; 
		return max;
	}

}
