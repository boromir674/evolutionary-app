package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import interfaces.Representation;
import evolutionaryAlgorithmComponents.AbstractRecombination;
import evolutionaryAlgorithmComponents.Individual;

public abstract class AbstractDiscreteRecombination extends AbstractRecombination {
	
	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.variationOperators.recombination.AbstractRecombination#applicableToPermutation()
	 */
	@Override
	public boolean applicableToPermutation() {
		return false;
	}

	public AbstractDiscreteRecombination(String title) {
		super(title);
	}

	@Override
	public boolean applicableToDiscrete() {
		return true;
	}

}
