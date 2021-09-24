package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

public class MultipleSigmasRepresentation extends RealValueRepresentation {
	
	private final static String title = " with one σ per gene";
	
	public MultipleSigmasRepresentation(double low, double high, double sigma, int dim){
		super(title, low, high, sigma, dim);
	}

	@Override
	public Object[] generateRandomChromosome(Random aRandom) {
		Double[] chromosome = new Double[2*dimensions];
		for (int i=0; i<dimensions; i++){
			chromosome[i] = aRandom.nextDouble() * 2*highestValue + lowestValue;
			chromosome[i+dimensions] = super.getInitialSigma();
		}
		return chromosome;
	}

	@Override
	public Object[] createEmptyChromosome() {
		return new Double[2*dimensions];
	}

}
