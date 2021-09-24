package evolutionaryAlgorithmComponents.variationOperators.recombination;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import interfaces.Recombination;
import interfaces.Representation;

public abstract class AbstractRecombination implements Recombination {

	private final String title;
	protected Random random;
	
	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public AbstractRecombination(String title) {
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@SuppressWarnings("static-method")
	protected Individual[] initializeTwoChildrenForRecombination(Representation aRepresentation) {
		Individual[] children = new Individual[]{Individual.getEmptyIndividual(aRepresentation), new Individual()};
		return children;
	}
	
	public abstract boolean applicableToPermutation();
	
}
