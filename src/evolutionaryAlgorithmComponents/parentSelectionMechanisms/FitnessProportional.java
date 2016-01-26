package evolutionaryAlgorithmComponents.parentSelectionMechanisms;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractParentSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class FitnessProportional extends AbstractParentSelection {
	
	private final static String title = "Fitness Proportional";
	
	public FitnessProportional(){
		super(title);
	}

	@Override
	public int[] select(Population pop, Random rand) throws Exception {
		// "Fitness Proportional Selection"
		// stochastically selects lambda parents, sampling according to probabilities based
		// on the fitness values of each member of the population
		double[] fitArray = Util.getFitnessArray(pop.getCurrentPopulation());
		double[] probabilities = Util.findFitnessBasedProbabilities(fitArray);
		double cumulativeProbs[] = Util.getCumulativeDistribution(probabilities);

		// array with indices to the pool ArrayList
		int[] parentPointers = Util.stochasticUniversalSampling(cumulativeProbs, pop.getLambda(), rand);
		return parentPointers;
	}

}
