package evolutionaryAlgorithmComponents.variationOperators.mutation.integerValue;

import interfaces.Representation;

import java.util.Random;

import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.representation.AbstractIntegerRepresentation;
import evolutionaryAlgorithmComponents.representation.AbstractRepresentation;
import evolutionaryAlgorithmComponents.variationOperators.mutation.AbstractMutation;

public class RandomResseting extends AbstractMutation {
	
	private static String title = "Random Resseting";
	private Representation rep;
	
	public RandomResseting(Representation aRep, double probabilityOfMutation) {
		super(title, probabilityOfMutation);
		rep = aRep;
	}

	@Override
	public boolean applicableToDiscrete() {
		return true;
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

}
