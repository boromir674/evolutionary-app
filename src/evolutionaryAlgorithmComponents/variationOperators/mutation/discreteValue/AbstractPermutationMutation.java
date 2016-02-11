package evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue;

import evolutionaryAlgorithmComponents.variationOperators.mutation.AbstractMutation;

public abstract class AbstractPermutationMutation extends AbstractDiscreteMutation {

	/* (non-Javadoc)
	 * @see evolutionaryAlgorithmComponents.variationOperators.mutation.AbstractMutation#applicableToPermutation()
	 */
	@Override
	public boolean applicableToPermutation() {
		return true;
	}

	public AbstractPermutationMutation(String title, double probability) {
		super(title, probability);
	}
	
	
}
