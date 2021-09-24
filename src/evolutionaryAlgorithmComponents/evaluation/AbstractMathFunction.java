package evolutionaryAlgorithmComponents.evaluation;

import evolutionaryAlgorithmComponents.AbstractEvaluationMethod;
import evolutionaryAlgorithmComponents.Individual;
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
		double fitness;
		if (this.hasMin())
			fitness = -this.f(values);
		else
			fitness = this.f(values);
		return fitness;
	}
	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.AbstractEvaluationMethod#getSolutionFitness()
	 */
	@Override
	public double getSolutionFitness() {
		Double[] temp = (Double[]) this.getSolutionVector();
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
