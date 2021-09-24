package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

public class OneSigmaPerIndividual extends RealValueRepresentation {
	
	public final static String title = "(x1, ., xn, σ1)";
	
	public OneSigmaPerIndividual(double low, double high, double sigma, int dim){
		super(low, high, sigma, dim);
	}

	@Override
	public Object[] generateRandomChromosome(Random aRandom) {
		Double[] chromosome = new Double[dimensions + 1];
		for (int i=0; i<dimensions; i++)
			chromosome[i] = aRandom.nextDouble() * (highestValue-lowestValue) + lowestValue;
		chromosome[dimensions] = super.getInitialSigma();
		return chromosome;
	}

	@Override
	public Object[] createEmptyChromosome() {
		return new Double[dimensions+1];
	}

}
