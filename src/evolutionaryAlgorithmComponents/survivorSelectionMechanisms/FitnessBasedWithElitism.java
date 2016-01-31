package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import Exceptions.SortsInPlaceThePopulationException;
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
	public int[] select(Population pop) {
		double[] fitarray = Util.getFitnessArray(pop.getPool(), pop.getPool().length);
		double[] probabilities = Util.findFitnessBasedProbabilities(fitarray);
		double[] cumulProbs = Util.getCumulativeDistribution(probabilities);
		int[] survivors = Util.stochasticUniversalSampling(cumulProbs, pop.getMu(), random);
		return survivors;
	}

	@Override
	public boolean isElitistic() {
		return true;
	}
}

