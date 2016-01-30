package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Arrays;
import java.util.Collections;
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
	public int[] select(Population pop) throws Exception {
		Individual fittest = pop.getFittestIndividualFromTheWholePool();
		double[] fitarray = Util.getFitnessArray(pop.getPool(), pop.getPool().length);
		double[] probabilities = Util.findFitnessBasedProbabilities(fitarray);
		double[] cumulProbs = Util.getCumulativeDistribution(probabilities);
		int[] survivors = Util.stochasticUniversalSampling(cumulProbs, pop.getMu(), random);
		for (int i=0; i<pop.getMu(); i++){
			Individual temp = pop.getPool()[survivors[i]];
			pop.getPool()[i] = temp;
		}
		if (pop.getFittestIndividual().getFitness() < fittest.getFitness()){
			pop.getPool()[random.nextInt(pop.getMu())] = fittest;
		}
		return null;
	}

}

