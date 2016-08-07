package evolutionaryAlgorithmComponents;

import java.util.Random;

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
	protected Individual[] initializeChildrenForRecombination(Representation aRepresentation) {
		Individual[] children = new Individual[]{new Individual(), new Individual()};
		children[0].setChromosome(aRepresentation.createEmptyChromosome());
		children[0].setRepresentation(aRepresentation);
		children[1].setChromosome(aRepresentation.createEmptyChromosome());
		children[1].setRepresentation(aRepresentation);
		return children;
	}
	
	public abstract boolean applicableToPermutation();
	
}
