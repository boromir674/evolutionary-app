package evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public class SwapMutation extends AbstractPermutationMutation {

	private final static String title = "Swap Mutation";

	public SwapMutation(double probability) {
		super(title, probability);
	}

	@Override
	public void apply(Individual in, Random aRandom) {
		if (aRandom.nextDouble() < super.getProbability()){
			int d = in.getRepresentation().getDimensions();
			int rand1 = aRandom.nextInt(d);
			int rand2 = aRandom.nextInt(d-1);
			if (rand2>=rand1)
				rand2 ++;

			Integer temp = (Integer) in.getChromosome()[rand1];
			in.getChromosome()[rand1] = in.getChromosome()[rand2];
			in.getChromosome()[rand2] = temp;
		}
	}
}
