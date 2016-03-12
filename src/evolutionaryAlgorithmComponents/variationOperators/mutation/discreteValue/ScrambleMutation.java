package evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.PermutationRepresentation;

public class ScrambleMutation extends AbstractPermutationMutation {

	private final static String title = "Scramble Mutation";

	public ScrambleMutation(double probability) {
		super(title, probability);
	}

	@Override
	public void apply(Individual in, Random aRandom) throws Exception {
		if (aRandom.nextDouble() < super.getProbability()){
			int d = in.getRepresentation().getDimensions();
			int start;
			int rand1 = aRandom.nextInt(d);
			int rand2 = aRandom.nextInt(d-1);
			if (rand1 <= rand2){
				rand2 ++;
				start = rand1;
			}else
				start = rand2;

			int length = Math.abs(rand2-rand1) + 1;
			int[] slice = new int[length];
			for (int i=0; i<length; i++)
				slice[i] = (Integer) in.getChromosome()[start+i];

			util.Util.shuffleArray(slice, aRandom);
			for (int i=0; i<length; i++)
				in.getChromosome()[start+i] = slice[i];
			PermutationRepresentation.chromosomeOK(in.getChromosome());
		}
	}
}
