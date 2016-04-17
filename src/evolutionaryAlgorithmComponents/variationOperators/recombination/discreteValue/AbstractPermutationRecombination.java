package evolutionaryAlgorithmComponents.variationOperators.recombination.discreteValue;


public abstract class AbstractPermutationRecombination extends AbstractDiscreteRecombination {

	public AbstractPermutationRecombination(String title) {
		super(title);
	}

	@Override
	public boolean applicableToPermutation() {
		return true;
	}

}
