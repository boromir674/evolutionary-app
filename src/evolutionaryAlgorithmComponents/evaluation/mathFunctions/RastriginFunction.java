package evolutionaryAlgorithmComponents.evaluation.mathFunctions;

import java.util.Properties;

import evolutionaryAlgorithmComponents.evaluation.AbstractMathFunction;
import exceptions.UnknownSolutionException;
import interfaces.HasProperties;

// This is an example evaluation. It is based on the Rastrigin. It is a maximization problem with a maximum of 10 for 
//  	vector a_.
// The Rastrigin function itself is for minimization with minimum of 0.
// Base performance is calculated as the distance of the expected fitness of a random search (with the same amount of available
//	evaluations) on the Rastrigin function to the function minimum, thus Base = E[f_best_random] - ftarget. Fitness is scaled
//	according to this base, thus Fitness = 10 - 10*(f-fbest)/Base

public class RastriginFunction extends AbstractMathFunction implements HasProperties{
	
	private final static String title = "Rastrigin Function";
	// Evaluations budget
	private final static int EVALS_LIMIT = 100000;
	// The base performance. It is derived by doing  random search on the F&P function (see function method) with the same
	//  amount of evaluations
	@SuppressWarnings("unused")
	private final static double BASE = 36.6939274039372;
	// The minimum of the Rastrigin function
	@SuppressWarnings("unused")
	private final static double ftarget = 0;
	// Properties of the Rastrigin function
	private final static String multimodal = "true";
	private final static String regular = "true";
	private final static String separable = "false";
	private static double ALPHA = 10;
	private String evals = Integer.toString(EVALS_LIMIT);

	public RastriginFunction() {
		super(title);
	}

	// The Rastrigin function. The global minimum is 0.
	@Override
	public double f(double[] x) {
		double sum = 0;
		for(int i=0; i<x.length; i++)
			sum += x[i]*x[i] - ALPHA*Math.cos(2*Math.PI*x[i]);
		return ALPHA*x.length + sum;	
	}

	@Override
	public Properties getProperties() {
		Properties properties = new Properties();
		properties.put("Multimodal", multimodal);
		properties.put("Regular", regular);
		properties.put("Separable", separable);
		properties.put("Evaluations", evals);
		return properties;
	}

	@Override
	public boolean hasMin() {
		return true;
	}

	@Override
	public Object[] getSolutionVector(int dimensionality) throws UnknownSolutionException {
		return new Double[dimensionality];
	}
}
