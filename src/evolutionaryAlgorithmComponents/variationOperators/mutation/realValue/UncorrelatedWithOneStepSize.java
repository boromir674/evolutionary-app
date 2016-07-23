package evolutionaryAlgorithmComponents.variationOperators.mutation.realValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.AbstractRealValueRepresentation;

public class UncorrelatedWithOneStepSize extends AbstractRealValueMutation {

	private final static String title = "Uncorrelated Mutation with One Step Size";
	private double t;

	public UncorrelatedWithOneStepSize(double probabilityOfMutation) {
		super(title, probabilityOfMutation);
	}

	@Override
	public void apply(Individual in, Random aRandom){
		if (aRandom.nextDouble() < super.getProbability()) {
			double N = aRandom.nextGaussian(); // N(0,1)
			int d = in.getRepresentation().getDimensions();
			initializeConstants(d);
			double newSigma = (double) in.getChromosome()[d] * Math.exp(t*N); // compute s'
			if (newSigma < epsilon) // check if s' < epsilon
				newSigma = epsilon;
			in.getChromosome()[d] = newSigma; // update s with s'
			for (int i=0; i<d; i++) // update x_i
				in.getChromosome()[i] = (double) in.getChromosome()[i] + ((AbstractRealValueRepresentation) in.getRepresentation()).ensureValueRange(newSigma * aRandom.nextGaussian());
		}
	}
	private void initializeConstants(int dimensions) {
		if (t==0)
			t = 1/Math.sqrt(dimensions);
	}
}