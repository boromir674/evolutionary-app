package evolutionaryAlgorithmComponents.variationOperators.mutation.realValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.RealValueRepresentation;

public class UncorrelatedWithNStepSizes extends AbstractRealValueMutation {	

	private static final String title = "Uncorrelated Mutation with n Step Sizes";
	private double tPrime = 0;
	private double t = 0;

	public UncorrelatedWithNStepSizes(double probabilityOfMutation) {
		super(title, probabilityOfMutation);
	}

	@Override
	public void apply(Individual in, Random aRandom){
		int d = in.getRepresentation().getDimensions();
		if (2*d != in.getChromosome().length){
			System.out.println("dimensions:"+d);
			System.out.println("length:"+in.getChromosome().length);
			System.out.println("2*dimensions should equal to length");
			System.exit(0);
		}

		if (aRandom.nextDouble() < super.getProbability()){
			initializeConstants(d);
			double N = aRandom.nextGaussian(); // N(0,1)
			double s_i;
			double x_i;

			for (int i=0; i<d; i++){
				double N_i = aRandom.nextGaussian();

				s_i = (double) in.getChromosome()[d+i] * Math.exp(tPrime*N + t*N_i); // compute s_i'
				if (s_i < epsilon) // check if s_i'< epsilon
					s_i = epsilon;
				in.getChromosome()[d+i] = s_i; // update s_i

				x_i = (double) in.getChromosome()[i] + s_i * N_i; // compute x_i'
				x_i = ((RealValueRepresentation) in.getRepresentation()).ensureValueRange(x_i); // check x for out of bounds
				in.getChromosome()[i] = x_i; // update x_i
			}
		}
	}

	private void initializeConstants(int dimensions) {
		if (t==0){
			tPrime = 1/Math.sqrt(2*dimensions);
			t = 1/Math.sqrt(2*Math.sqrt(dimensions));
		}
	}

}
