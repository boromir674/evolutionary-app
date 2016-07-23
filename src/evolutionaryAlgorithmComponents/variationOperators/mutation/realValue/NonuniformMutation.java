package evolutionaryAlgorithmComponents.variationOperators.mutation.realValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.OneSigmaPerPopulation;
import evolutionaryAlgorithmComponents.representation.AbstractRealValueRepresentation;

public class NonuniformMutation extends AbstractRealValueMutation {
	//TODO check implementation
	private final static String title = "Nonuniform";
	private double t = 0;
	private double sigma = 0;

	public NonuniformMutation(double probabilityOfMutation) {
		super(title, probabilityOfMutation);
	}

	@Override
	public void apply(Individual in, Random aRandom) {
		if (aRandom.nextDouble() < super.getProbability()){
			double N = aRandom.nextGaussian(); // N(0,1)
			int d = in.getRepresentation().getDimensions();
			if (t == 0) {
				t = 1/Math.sqrt(d);
				sigma = ((OneSigmaPerPopulation) in.getRepresentation()).getInitialSigma();
			}
			sigma *= Math.exp(t*N); // compute s'
			if (sigma < epsilon) // check if s' < epsilon
				sigma = epsilon;
			for (int i=0; i<d; i++) // update x_i
				in.getChromosome()[i] = (double) in.getChromosome()[i] + ((AbstractRealValueRepresentation) in.getRepresentation()).ensureValueRange(sigma * aRandom.nextGaussian());
		}
	}
}
