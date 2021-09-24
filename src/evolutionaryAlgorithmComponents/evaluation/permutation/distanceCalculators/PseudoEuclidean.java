package evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators;

import interfaces.DistanceCalculator;

public class PseudoEuclidean implements DistanceCalculator {

	public PseudoEuclidean() {

	}
	
	/**
	 * Calculates the pseudo-Euclidean distance between the given coordinates
	 * of the two points.
	 */
	@Override
	public int calculateDistance(double[] coords1, double[] coords2) {
		double xd = coords1[0] - coords2[0];
		double yd = coords1[1] - coords2[1];
		double rij = Math.sqrt( (xd*xd + yd*yd) / 10.0 );
		int tij = Math.round((float)rij);
		if (tij<rij)
			return tij + 1;
		else
			return tij;
	}

}
