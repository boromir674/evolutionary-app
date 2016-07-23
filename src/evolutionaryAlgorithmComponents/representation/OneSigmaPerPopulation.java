package evolutionaryAlgorithmComponents.representation;		

import java.util.Random;

public class OneSigmaPerPopulation extends AbstractRealValueRepresentation {
	
	private final static String title = " with a single σ for the whole population";
	
	public OneSigmaPerPopulation(double low, double high, double sigma, int dim){
		super(low, high, sigma, dim);
	}

	@Override
	public Object[] generateRandomChromosome(Random aRandom) throws Exception {
		Double[] chromosome = new Double[dimensions];
		for (int i=0; i<dimensions; i++)
			chromosome[i] = aRandom.nextDouble() * (highestValue-lowestValue) + lowestValue;
		return chromosome;
	}

	@Override
	public Object[] createEmptyChromosome() {
		return new Integer[dimensions];
	}

}
