package evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;

public class InversionMutation extends AbstractPermutationMutation {

	private static final String title = "Inversion Mutation";

	public InversionMutation(double probability) {
		super(title, probability);
	}

	@Override
	public void apply(Individual in, Random aRandom) {
		if (aRandom.nextDouble() < super.getProbability()){
			int d = in.getRepresentation().getDimensions();
			int rand1 = aRandom.nextInt(d);
			int rand2 = aRandom.nextInt(d-1);
			int start;
			if (rand1<=rand2){
				start = rand1;
				rand2 ++;
			}else
				start = rand2;

			int length = Math.abs(rand1 - rand2) + 1;
			int[] slice = new int[length];

			for (int i=0; i<length; i++)
				slice[i] = (Integer)(in.getChromosome()[start+i]);
			for (int i=0; i<length; i++)
				in.getChromosome()[start+i] = slice[length-1-i];
		}
	}
}
