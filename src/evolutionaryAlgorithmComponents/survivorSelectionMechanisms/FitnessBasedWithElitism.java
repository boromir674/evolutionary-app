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
		double[] fitArray = new double[pop.getPool().length];
		for (int i=0; i<fitArray.length; i++)
			fitArray[i] = pop.getPool()[i].getFitness();
		double minFitness = Util.findMin(fitArray);
		double fitnessSum = 0;
		for (int i=0; i<fitArray.length; i++) {
			fitArray[i] = fitArray[i] - minFitness + 1;
			fitnessSum += fitArray[i];
		}
		double[] cumulProbs = new double[fitArray.length];
		cumulProbs[0] = fitArray[0]/fitnessSum;
		for (int i=1; i<fitArray.length; i++){	
			cumulProbs[i] = cumulProbs[i-1] + fitArray[i]/fitnessSum;
		}
		int[] survivors = Util.stochasticUniversalSampling(cumulProbs, pop.getMu(), random);
		return survivors;
	}

	@Override
	public boolean forceElitism() {
		return true;
	}
}
