package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import java.util.Random;

import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.Population;

public class UniformSelection extends AbstractParentSelection {
	
	private final static String title = "Uniform Selection";
	
	public UniformSelection() {
		super(title);
	}

	@Override
	public int[] select(Population pop, Random rand) {
		// Uniform Sample Selection
		// requires strong fitness-based survivor selection
		// stochastically selects lambda parents, sampling uniformly
		int rand1, rand2;
		int[] parentPointers = new int[pop.getLambda()];
		for (int i=0; i<pop.getLambda(); i=i+2) {
			rand1 = rand.nextInt(pop.getMu());
			rand2 = rand.nextInt(pop.getMu()-1);
			if (rand2 > rand1)
				rand2++;
			parentPointers[i] = rand1;
			parentPointers[i+1] = rand2;
		}
		return parentPointers;
	}
	
}
