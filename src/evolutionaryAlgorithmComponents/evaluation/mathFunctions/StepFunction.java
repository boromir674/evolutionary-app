package evolutionaryAlgorithmComponents.evaluation.mathFunctions;

import evolutionaryAlgorithmComponents.evaluation.AbstractMathFunction;
import exceptions.UnknownSolutionException;

public class StepFunction extends AbstractMathFunction {
	
	private final static String title = "Step Function";
	public StepFunction() {
		super(title);
	}

	@Override
	public double f(double[] x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasMin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] getSolutionVector(int dimensionality)
			throws UnknownSolutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
