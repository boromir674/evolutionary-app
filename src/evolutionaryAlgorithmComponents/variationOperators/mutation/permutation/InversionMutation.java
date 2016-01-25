package evolutionaryAlgorithmComponents.variationOperators.mutation.permutation;

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

			int number = Math.abs(rand1 - rand2) + 1;
			int[] temp = new int[number];

			for (int i=0; i<number; i++)
				temp[i] = (Integer)(in.getChromosome()[start+i]);
			for (int i=0; i<number; i++)
				in.getChromosome()[start+i] = temp[number-1-i];
		}
	}
}
