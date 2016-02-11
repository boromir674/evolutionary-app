package evolutionaryAlgorithmComponents.variationOperators.mutation.discreteValue;

import interfaces.Representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.AbstractRepresentation;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.AbstractIntegerRepresentation;
import evolutionaryAlgorithmComponents.variationOperators.mutation.AbstractMutation;

public class RandomResseting extends AbstractDiscreteMutation {
	
	private static String title = "Random Resseting";
	private Representation rep;
	
	public RandomResseting(Representation aRep, double probabilityOfMutation) {
		super(title, probabilityOfMutation);
		rep = aRep;
	}

	@Override
	public void apply(Individual in, Random aRandom) {
		
		double rand;
		int d = in.getRepresentation().getDimensions();
		int[] lowHigh = ((AbstractIntegerRepresentation) rep).getLowAndHigh();
				
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
