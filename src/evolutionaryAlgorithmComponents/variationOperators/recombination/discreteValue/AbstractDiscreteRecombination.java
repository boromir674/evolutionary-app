package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;

import interfaces.Representation;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.variationOperators.recombination.AbstractRecombination;

public abstract class AbstractDiscreteRecombination extends AbstractRecombination {
	
	public AbstractDiscreteRecombination(String title) {
		super(title);
	}

	@Override
	public boolean applicableToDiscrete() {
		return true;
	}
	
	public abstract boolean applicableToPermutation();

}
