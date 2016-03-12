package evolutionaryAlgorithmComponents.variationOperators.mutation.realValue;

import evolutionaryAlgorithmComponents.AbstractMutation;

public abstract class AbstractRealValueMutation extends AbstractMutation {
	
	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.variationOperators.mutation.AbstractMutation#applicableToPermutation()
	 */
	@Override
	public boolean applicableToPermutation() {
		return false;
	}

	protected final static double epsilon = 0.001;
	
	public AbstractRealValueMutation(String title, double probabilityOfMutation) {
		super(title, probabilityOfMutation);
	}

	@Override
	public boolean applicableToDiscrete() {
		return false;
	}

}
