package evolutionaryAlgorithmComponents.evaluation.realValueEvaluations;

import interfaces.HasProperties;
import interfaces.MathFunction;

import java.util.Properties;

import evolutionaryAlgorithmComponents.Individual;

public class SphereFunction extends AbstractMathFunction implements HasProperties{
	
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
	
	/*public Object scaledScore(Object[] chromosome) {
		if(evaluations > EVALS_LIMIT) return null;
		Double[] chrom = new Double[chromosome.length];
		for (int i=0; i<chrom.length; i++)
			chrom[i] = (Double) chromosome[i];
		// Transform function value (sphere is minimization).
		// Normalize using the base performance
		double f = 10 - 10*( (function(chrom)-ftarget) / BASE ) ;

		if(f>best)
			best = f;
		evaluations++;

		return new Double(f);
	}*/

	@Override
	public double f(double[] x) {
		// The standard sphere function. It has one minimum at 0.
		double sum = 0;
		for(int i=0; i<x.length; i++)
			sum += x[i]*x[i];
		//System.out.println(sum);
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
	public boolean isMin() {
		return true;
	}

}
