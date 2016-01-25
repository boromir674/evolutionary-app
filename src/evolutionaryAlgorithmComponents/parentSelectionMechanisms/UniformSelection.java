package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import java.util.Random;

import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class UniformSelection extends AbstractParentSelection {
	
	private final static String title = "Uniform Selection";
	
	public UniformSelection() {
		super(title);
	}

	@Override
	public Individual[] select(Population aPopulation, Random aRandom) {
		// Uniform Sample Selection
		// requires strong fitness-based survivor selection
		// stochastically selects lambda parents, sampling uniformly
		int[] matingPool = new int[aPopulation.getLambda()];
		for (int i=0; i<aPopulation.getLambda(); i++)
			matingPool[i] = aRandom.nextInt(aPopulation.getParentsAndChildren().size());
		Individual[] parents = new Individual[aPopulation.getLambda()];
		for (int i=0; i<parents.length; i++)
			parents[i] = aPopulation.member(matingPool[i]);
		return parents;
	}
	
}
