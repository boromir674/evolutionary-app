package evolutionaryAlgorithmComponents.variationOperators.mutation.permutation;

import evolutionaryAlgorithmComponents.variationOperators.mutation.AbstractMutation;

public abstract class AbstractPermutationMutation extends AbstractMutation {

	public AbstractPermutationMutation(String title, double probability) {
		super(title, probability);
	}

	@Override
	public boolean applicableToDiscrete() {
		return true;
	}

}
