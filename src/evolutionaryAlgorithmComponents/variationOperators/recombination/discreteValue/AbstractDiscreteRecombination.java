package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import interfaces.Representation;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.variationOperators.recombination.AbstractRecombination;

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
