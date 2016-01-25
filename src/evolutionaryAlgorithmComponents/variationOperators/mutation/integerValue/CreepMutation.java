package evolutionaryAlgorithmComponents.variationOperators.mutation.integerValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.variationOperators.mutation.AbstractMutation;
@Deprecated
public class CreepMutation extends AbstractMutation {
	
	private final static String title = "Creep Mutation";
	
	public CreepMutation(double probability) {
		super(title, probability);
	}

	@Override
	public void apply(Individual anIndividual, Random aRandom) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean applicableToDiscrete() {
		return true;
	}

}
