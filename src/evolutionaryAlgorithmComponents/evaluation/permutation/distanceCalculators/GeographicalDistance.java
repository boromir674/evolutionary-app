package evolutionaryAlgorithmComponents.evaluation.permutation.distanceCalculators;

import interfaces.DistanceCalculator;

public class GeographicalDistance implements DistanceCalculator {
	
	private final static double PI = 3.141592;
	private final static double RRR = 6378.388;
	
	public GeographicalDistance() {

	}
	
	/**
	 * In case the tsp is a geographical problem, it calculates the distance in kilometers between the given coordinates
	 * of the two points, after converting them to longitude and latitude.
	 */
	@Override
	public int calculateDistance(double[] coords1, double[] coords2) {
		double[] latlon1 = GeographicalDistance.getLatLong(coords1);
		double[] latlon2 = GeographicalDistance.getLatLong(coords2);
		double q1 = Math.cos(latlon1[1] - latlon2[1]);
		double q2 = Math.cos(latlon1[0] - latlon2[0]);
		double q3 = Math.cos(latlon1[0] + latlon2[0]);
		return (int) (RRR * Math.acos(0.5*((1.0+q1)*q2- (1.0-q1)*q3) ) +1.0);
	}
	
	private static double[] getLatLong(double[] coords){
		double lat, lon;
		int deg = Math.round((float)coords[0]);
		double min = coords[0] - deg;
		lat = PI * (deg + 5.0 * min / 3.0) / 180.0;
		deg = Math.round((float)coords[1]);
		min = coords[1] - deg;
		lon = PI * (deg + 5.0 * min / 3.0) / 180.0;
		return new double[]{lat, lon};
	}

}
