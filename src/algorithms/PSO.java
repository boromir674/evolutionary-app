package algorithms;
@SuppressWarnings("unused")
public class PSO {
	
	// Paper, C., & Sciences, A. (2004). Analysis of Particle Swarm Optimization Using Computational Statistics, (JANUARY). http://doi.org/10.1002/anac.200410007

	// χ = 2 * κ / |2-φ-sqrt(φ^2-4φ)| = 0.729843788
	//private final static int k = 1;
	//private final static double f = 4.1;

	//------------ EXOGENEOUS PARAMETERS

	// swarm size                                : s
	// constriction coefficient                  : x
	// multiplier for random numbers             : phi
	// maximum value of the step size (velocity) : uMax

	private final static int s = 40;
	private final static double x = 0.729843788;
	private final static double phi = 4.1;
	private final static int uMax = 100;

}
