package evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.AbstractIntegerRepresentation;

public class RandomResseting extends AbstractDiscreteMutation {
	
	private static String title = "Random Resseting";
	
	public RandomResseting(double probabilityOfMutation) {
		super(title, probabilityOfMutation);
	}

	@Override
	public void apply(Individual in, Random aRandom) {
		
		double rand;
		int d = in.getRepresentation().getDimensions();
		int[] lowHigh = ((AbstractIntegerRepresentation) in.getRepresentation()).getLowAndHigh();
				
		for (int i=0; i<d; i++){
			rand = aRandom.nextDouble();
			if (rand < super.getProbability())				
				in.getChromosome()[i] = aRandom.nextInt(lowHigh[1]-lowHigh[0]+1) + lowHigh[0];
		}
	}

	@Override
	public boolean applicableToPermutation() {
		return false;
	}

}
