package evolutionaryAlgorithmComponents.representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public abstract class AbstractRealValueRepresentation extends AbstractRepresentation {
	
	private final static String title = "Real-Value";
	protected double lowestValue;
	protected double highestValue;
	private final double initialSigma;	
	
	public AbstractRealValueRepresentation(double low, double high, double sigma, int dim){
		super(title, dim);
		lowestValue = low;
		highestValue = high;
		initialSigma = sigma;
		dimensions = dim;
	}
	
	public void setDimensions(int d){
		this.dimensions = d;
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
	public double genotypicDistance(Individual anIndividual0,
			Individual anIndividual1) {
		// TODO Auto-generated method stub
		return 0;
	}


}
