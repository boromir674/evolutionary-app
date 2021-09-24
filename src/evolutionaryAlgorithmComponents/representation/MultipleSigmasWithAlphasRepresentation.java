package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

public class MultipleSigmasWithAlphasRepresentation extends	RealValueRepresentation {
	
	public final static String title = "(x1, ., xn, σ1, ., σn, a1, ., a_{n(n-1)/2})";
	private double initAlpha;
	
	public MultipleSigmasWithAlphasRepresentation(double low, double high, double initialSigma, double alpha, int dim) {
		super(low, high, initialSigma, dim);
		initAlpha = alpha;
	}
	
	@Override
	public Object[] generateRandomChromosome(Random aRandom) {
		Double[] chromosome = new Double[2*dimensions+dimensions*(dimensions-1)/2];
		for (int i=0; i<dimensions; i++){
			chromosome[i] = super.uniformSample(aRandom);
			chromosome[i+dimensions] = super.getInitialSigma();
		}
		for (int i=2*dimensions; i<chromosome.length; i++)
			chromosome[i] = initAlpha;
		return chromosome;
	}
	
	@Override
	public Object[] createEmptyChromosome() {
		return new Double[2*dimensions+dimensions*(dimensions-1)/2];
	}
	
}
