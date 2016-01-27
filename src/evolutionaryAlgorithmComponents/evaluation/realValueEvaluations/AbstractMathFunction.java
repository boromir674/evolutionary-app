package evolutionaryAlgorithmComponents.evaluation.realValueEvaluations;

import evolutionaryAlgorithmComponents.AbstractEvaluationMethod;
import evolutionaryAlgorithmComponents.Individual;
import interfaces.MathFunction;

public abstract class AbstractMathFunction extends AbstractEvaluationMethod implements MathFunction {

	/* (non-Javadoc)
	 * @see interfaces.EvaluationMethod#getSolutionVector()
	 */
	@Override
	public Object[] getSolutionVector() {
		// TODO Auto-generated method stub
		return null;
	}

	public AbstractMathFunction(String title) {
		super(title);
	}

	@Override
	public double computeFitness(Individual anIndividual) {
		double[] values = new double[anIndividual.getRepresentation().getDimensions()];
		for (int i=0; i<values.length; i++)
			values[i] = (double) anIndividual.getChromosome()[i];
		double fitness;
		if (this.hasMin())
			fitness = -this.f(values);
		else
			fitness = this.f(values);
		super.evaluationsUsed ++;
		return fitness;
	}
}
