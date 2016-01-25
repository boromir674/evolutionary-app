package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.Population;

public class FitnessBasedWithElitism extends AbstractSurvivorSelection {

	private final static String title = "Stochastic Fitness-based with Elitism";
	private Random random;
	
	public FitnessBasedWithElitism(Random aRandom) {
		super(title);
		random = aRandom;
	}

	@Override
	public void select(Population aPopulation) throws Exception {
		// stochastically selects survivors, sampling according to probabilities based
		// on the fitness values of each member of the population while preserving the
		// top performing individual at every generation
		// Updates the population

		double[] fitArray = Util.getFitnessArray(aPopulation);

		int bestIndex = Util.findMaxIndex(fitArray); // index of member with best fitness value 

		double[] probabilities = Util.findFitnessBasedProbabilities(fitArray);
		double cumulativeProbs[] = Util.getCumulativeDistribution(probabilities);

		// array with indices pointing to members for keeping
		//int[] survivors = Utils.rouletteWheel(cumulativeProbs, mu, poolRandom);
		int[] survivors = Util.stochasticUniversalSampling(cumulativeProbs, aPopulation.getMu(), random);

		// check if best individual is kept
		boolean kept = false;
		int j = 0;
		while (!kept & j<survivors.length){
			if (survivors[j] == bestIndex)
				kept = true;
			j ++;
		}
		if (!kept){ // if best not kept, replace a random member with it
			int s = random.nextInt(aPopulation.getMu());
			survivors[s] = bestIndex;
		}			

		// store members picked by the roulette at the top μ positions
		for (int i=0; i<aPopulation.getMu(); i++)
			aPopulation.set(i, aPopulation.member(survivors[i]));
		aPopulation.getParentsAndChildren().subList(aPopulation.getMu(), aPopulation.getParentsAndChildren().size()).clear(); // discard all after top μ
		super.select(aPopulation);
	}

}

