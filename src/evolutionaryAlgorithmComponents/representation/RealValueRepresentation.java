package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.AbstractRepresentation;

public class RealValueRepresentation extends AbstractRepresentation {
	
	private final static String title = "Real-Value";
	protected double lowestValue;
	protected double highestValue;
	private final double initialSigma;	
	
	public RealValueRepresentation(String title, double low, double high, double sigma, int dim){
		super(RealValueRepresentation.title + title, dim);
		lowestValue = low;
		highestValue = high;
		initialSigma = sigma;
		dimensions = dim;
	}
	
	public double ensureValueRange(double value){
		double result = value;
		if (value < lowestValue)
			result = highestValue - (lowestValue - value)%(highestValue-lowestValue);
		else if (value > highestValue)
			result = lowestValue + (value - highestValue)%(highestValue-lowestValue);
		return result;
	}
	/**
	 * Returns a number uniformly sampled from the interval [this.lowestValue, this.highestValue].
	 * @param aRandom an instance of class Random.
	 * @return the sample.
	 */
	public double uniformSample(Random aRandom){
		return lowestValue + (highestValue - lowestValue) * aRandom.nextDouble();
	}
	
	public double getInitialSigma() {
		return initialSigma;
	}

	@Override
	public Object[] generateRandomChromosome(Random aRandom) {
		Double[] chromosome = new Double[dimensions];
		for (int i=0; i<dimensions; i++)
			chromosome[i] = aRandom.nextDouble() * 2*highestValue + lowestValue;
		return chromosome;
	}

	@Override
	public Object[] createEmptyChromosome() {
		return new Double[dimensions];
	}

	/**
	 * @return the lowestValue
	 */
	public double getLowestValue() {
		return lowestValue;
	}

	/**
	 * @return the highestValue
	 */
	public double getHighestValue() {
		return highestValue;
	}

	@Override
	public double genotypicDistance(Object[] chromosome, Object[] chromosome2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
