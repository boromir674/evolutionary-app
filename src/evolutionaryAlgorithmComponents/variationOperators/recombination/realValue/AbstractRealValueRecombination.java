package evolutionaryAlgorithmComponents.variationOperators.recombination.realValue;

import evolutionaryAlgorithmComponents.AbstractRecombination;

public abstract class AbstractRealValueRecombination extends AbstractRecombination {
	
	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.variationOperators.recombination.AbstractRecombination#applicableToPermutation()
	 */
	@Override
	public boolean applicableToPermutation() {
		return false;
	}

	protected final static double a = 0.5;
	
	public AbstractRealValueRecombination(String title) {
		super(title);
	}

	/* (non-Javadoc)
	 * @see interfaces.Recombination#applicableToDiscrete()
	 */
	@Override
	public boolean applicableToDiscrete() {
		return false;
	}	

}
