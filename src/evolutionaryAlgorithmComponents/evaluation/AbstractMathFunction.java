package evolutionaryAlgorithmComponents.evaluation;

import evolutionaryAlgorithmComponents.AbstractEvaluationMethod;
import evolutionaryAlgorithmComponents.Individual;
import exceptions.UnknownSolutionException;
import interfaces.MathFunction;

public abstract class AbstractMathFunction extends AbstractEvaluationMethod implements MathFunction {

	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.AbstractEvaluationMethod#calculateFitness(evolutionaryAlgorithmComponents.Individual)
	 */
	@Override
	protected double calculateFitness(Individual anIndividual) {
		double[] values = new double[anIndividual.getRepresentation().getDimensions()];
		for (int i=0; i<values.length; i++)
			values[i] = (double) anIndividual.getChromosome()[i];
		if (this.hasMin())
			return f(values);
		return f(values);
	}
	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.AbstractEvaluationMethod#getSolutionFitness()
	 */
	@Override
	public double getSolutionFitness(int dimensionality) throws UnknownSolutionException {
		Double[] temp = (Double[]) this.getSolutionVector(dimensionality);
		double[] values = new double[temp.length];
		for (int i=0; i<values.length; i++)
			values[i] = temp[i];
		if (this.hasMin())
			return -this.f(values);
		else
			return this.f(values);
	}

	public AbstractMathFunction(String title) {
		super(title);
	}

}
