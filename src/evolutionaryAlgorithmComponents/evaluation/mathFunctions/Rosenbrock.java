package evolutionaryAlgorithmComponents.evaluation.mathFunctions;

import evolutionaryAlgorithmComponents.evaluation.AbstractMathFunction;

/**
 * @author kostas
 *
 */
public class Rosenbrock extends AbstractMathFunction {
	
	final static String title = "Rosenbrock Fuction";
	
	public Rosenbrock() {
		super(title);
	}

	@Override
	public double f(double[] values) {
		double sum = 0;
		for (int i=0; i<values.length-1; i++)
			sum += 100 * Math.pow(values[i+1]-Math.pow(values[i],2), 2) + Math.pow(1-values[i], 2);
		return sum;
	}

	@Override
	public boolean hasMin() {
		return true;
	}

	@Override
	public Object[] getSolutionVector(int dimensionality) {
		Double[] sol = new Double[dimensionality];
		for (int i=0; i<dimensionality; i++)
			sol[i] = 1.0;
		return sol;
	}

}
