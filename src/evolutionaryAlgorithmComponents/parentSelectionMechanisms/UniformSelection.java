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
	public Individual[] select(Population pop, Random rand) {
		// Uniform Sample Selection
		// requires strong fitness-based survivor selection
		// stochastically selects lambda parents, sampling uniformly
		int rand1, rand2;
		Individual[] parents = new Individual[pop.getLambda()];
		for (int i=0; i<pop.getLambda(); i=i+2) {
			rand1 = rand.nextInt(pop.getMu());
			rand2 = rand.nextInt(pop.getMu()-1);
			if (rand2 > rand1)
				rand2++;
			parents[i] = pop.getPool()[rand1];
			parents[i+1] = pop.getPool()[rand2];
		}
		return parents;
	}
	
}
