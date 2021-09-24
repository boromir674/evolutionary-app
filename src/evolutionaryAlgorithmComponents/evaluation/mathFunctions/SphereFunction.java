package evolutionaryAlgorithmComponents.evaluation.mathFunctions;

import interfaces.HasProperties;

import java.util.Properties;

import evolutionaryAlgorithmComponents.evaluation.AbstractMathFunction;

public class SphereFunction extends AbstractMathFunction implements HasProperties{
	
	private int cheatD;
	
	private final static String title = "Sphere Function";
	// Evaluations budget
	private final static int EVALS_LIMIT = 10000;
	// The base performance. It is derived by doing random search on the sphere function (see function method) with the same
	//  amount of evaluations
	@SuppressWarnings("unused")
	private final static double BASE = 11.5356;
	// The minimum of the sphere function
	@SuppressWarnings("unused")
	private final static double ftarget = 0;

	// Properties of the Sphere function
	private final static String multimodal = "false";
	private final static String regular = "true";
	private final static String separable = "true";
	private String evals = Integer.toString(EVALS_LIMIT);

	public SphereFunction() {
		super(title);
	}

	@Override
	public double f(double[] x) {
		cheatD = x.length;
		// The standard sphere function. It has one minimum at 0.
		double sum = 0;
		for(int i=0; i<x.length; i++)
			sum += x[i]*x[i];
		return sum;
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
	public Object[] getSolutionVector(){
		Double[] solution = new Double[cheatD];
		for (int i=0; i<solution.length; i++)
			solution[i] = 0.0;
		return solution;
	}

}
