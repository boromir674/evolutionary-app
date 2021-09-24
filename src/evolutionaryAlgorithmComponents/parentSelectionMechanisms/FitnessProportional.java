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
	public Individual[] select(Population aPopulation, Random aRandom) throws Exception {
		// "Fitness Proportional Selection"
		// stochastically selects lambda parents, sampling according to probabilities based
		// on the fitness values of each member of the population
		double[] fitArray = Util.getFitnessArray(aPopulation);
		double[] probabilities = Util.findFitnessBasedProbabilities(fitArray);
		double cumulativeProbs[] = Util.getCumulativeDistribution(probabilities);

		// array with indices to the pool ArrayList
		int[] matingPool = Util.stochasticUniversalSampling(cumulativeProbs, aPopulation.getLambda(), aRandom);
		//matingPool = Utils.rouletteWheel(cumulativeProbs, lambda, poolRandom);	
		Individual[] parents = new Individual[aPopulation.getLambda()];
		for (int i=0; i<parents.length; i++)
			parents[i] = aPopulation.member(matingPool[i]);
		return parents;
	}

}
