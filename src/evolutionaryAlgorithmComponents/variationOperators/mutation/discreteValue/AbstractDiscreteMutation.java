package evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue;

import evolutionaryAlgorithmComponents.AbstractMutation;

public abstract class AbstractDiscreteMutation extends AbstractMutation {

	/* (non-Javadoc)
	 * @see interfaces.Mutation#applicableToDiscrete()
	 */
	@Override
	public boolean applicableToDiscrete() {
		return true;
	}

	public AbstractDiscreteMutation(String title, double probability) {
		super(title, probability);
	}

}
