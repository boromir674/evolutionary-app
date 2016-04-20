package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

public class MultipleSigmasWithAlphasRepresentation extends	RealValueRepresentation {
	
	private final static String title = " with multiple σ's and a's";
	private double initAlpha;
	
	public MultipleSigmasWithAlphasRepresentation(double low, double high, double sigma, double alpha, int dim) {
		super(low, high, sigma, dim);
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
