package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class FitnessBasedWithElitism extends AbstractSurvivorSelection {

	private final static String title = "Stochastic Fitness-based with Elitism";
	private Random random;

	public FitnessBasedWithElitism(Random aRandom) {
		super(title);
		random = aRandom;
	}

	@Override
	public void select(Population pop) throws Exception {
		// stochastically selects survivors, sampling according to probabilities based
		// on the fitness values of each member of the population while preserving the
		// top performing individual at every generation
		// Updates the population

		double[] fitArray = Util.getFitnessArray(pop.getPool(), pop.getPool().length);

		int bestIndex = Util.findMaxIndex(fitArray); // index of member with best fitness value
		boolean flag = false;
		for (int i=0; i<pop.getLambda(); i++){
			if (pop.getPool()[i].getFitness() > pop.getPool()[bestIndex].getFitness()){
				flag = true;
				break;
			}
		}
		if (flag)
			throw new Exception("max found is not really max");
		//Individual i1 = pop.getPool()[bestIndex];
		//Individual i2 = pop.getFittestIndividual();
		
		double[] probabilities = Util.findFitnessBasedProbabilities(fitArray);
		double cumulativeProbs[] = Util.getCumulativeDistribution(probabilities);

		// array with indices pointing to members for keeping
		int[] survivors = Util.rouletteWheel(cumulativeProbs, pop.getMu(), random);
		//int[] survivors = Util.stochasticUniversalSampling(cumulativeProbs, pop.getMu(), random);

		// check if best individual is kept
		flag = false;
		int j = 0;
		while (!flag & j<survivors.length){
			if (survivors[j] == bestIndex)
				flag = true;
			j ++;
		}
		if (!flag){ // if best not kept, replace a random member with it
			int s = random.nextInt(pop.getMu());
			survivors[s] = bestIndex;
		}			

		// store members picked by the roulette at the top μ positions
		for (int i=0; i<pop.getMu(); i++){
			Individual temp = pop.getPool()[survivors[i]];
			pop.getPool()[i] = temp;
		}
	}

}

