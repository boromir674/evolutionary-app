package evolutionaryAlgorithmComponents.variationOperators.mutation;

import java.util.Random;

import evolutionaryAlgorithmComponents.AbstractMutation;
import evolutionaryAlgorithmComponents.Individual;

public class Bitwise extends AbstractMutation {

	private static String title = "Bitwise Mutation";

	public Bitwise(double mutationRate) {
		super(title, mutationRate);
	}

	@Override
	public void apply(Individual anIndividual, Random aRandom) {

		double rand;
		int d = anIndividual.getRepresentation().getDimensions();

		for (int i=0; i<d; i++){
			rand = aRandom.nextDouble();
			if (rand < super.getProbability())
				anIndividual.getChromosome()[i] = (byte)( ((byte)(anIndividual.getChromosome()[i]) + 1) % 2) ;
		}
	}

	@Override
	public boolean applicableToPermutation() {
		return false;
	}

	@Override
	public boolean applicableToDiscrete() {
		return false;
	}

}
