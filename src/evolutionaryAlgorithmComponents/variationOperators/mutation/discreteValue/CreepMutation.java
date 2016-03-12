package evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
@Deprecated
public class CreepMutation extends AbstractDiscreteMutation {
	
	private final static String title = "Creep Mutation";
	
	public CreepMutation(double probability) {
		super(title, probability);
	}

	@Override
	public void apply(Individual anIndividual, Random aRandom) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean applicableToPermutation() {
		return false;
	}

}
