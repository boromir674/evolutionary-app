package evolutionaryAlgorithmComponents.variationOperators.recombination;

import evolutionaryAlgorithmComponents.Individual;
import interfaces.EvolutionaryAlgorithmComponent;
import interfaces.Recombination;
import interfaces.Representation;

public abstract class AbstractRecombination implements Recombination {

	private final String title;

	public AbstractRecombination(String title) {
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@SuppressWarnings("static-method")
	protected Individual[] initializeChildrenForRecombination(Representation aRepresentation) {
		Individual[] children = new Individual[]{new Individual(), new Individual()};
		children[0].initializeEmpty(aRepresentation);
		children[1].initializeEmpty(aRepresentation);
		return children;
	}

}
