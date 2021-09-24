package evolutionaryAlgorithmComponents.variationOperators.mutation;

import interfaces.EvolutionaryAlgorithmComponent;
import interfaces.Mutation;

public abstract class AbstractMutation implements Mutation{

	private final String title;
	private double probability;

	public AbstractMutation(String title, double probability){
		this.title = title;
		this.probability = probability;
	}

	/* (non-Javadoc)
	 * @see interfaces.EvolutionaryAlgorithmComponent#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see interfaces.Mutation#getProbability()
	 */
	@Override
	public double getProbability() {
		return probability;
	}
	
}
