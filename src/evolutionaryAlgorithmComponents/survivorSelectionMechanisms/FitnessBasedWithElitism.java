package evolutionaryAlgorithmComponents.survivorSelectionMechanisms;

import interfaces.FitnessCalculator;

import java.util.Random;

import util.Util;
import evolutionaryAlgorithmComponents.AbstractSurvivorSelection;
import evolutionaryAlgorithmComponents.Individual;
import evolutionaryAlgorithmComponents.Population;

public class FitnessBasedWithElitism extends AbstractSurvivorSelection {

	private final static String title = "Stochastic Fitness-based with Elitism";
	private FitnessCalculator fitnessCalculator;

	public FitnessBasedWithElitism() {
		super(title);
		this.fitnessCalculator = new FitnessCalculator() {
			@Override
			public double computeFitness(Individual anIndividual) {
				return anIndividual.getFitness();
			}
		};
	}
	public FitnessBasedWithElitism(Random aRandom, FitnessCalculator aFitnessCalculator) {
		super(title);
		this.fitnessCalculator = aFitnessCalculator;
	}

	@Override
	public int[] select(Population pop) {
/*		double[] fitArray = new double[pop.getPool().length];
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
		}*/
		double[] cumulProbs = util.Util.getCumulativeDistribution(pop.getPool(), 0, pop.getPool().length, fitnessCalculator);
		int[] survivors = Util.stochasticUniversalSampling(cumulProbs, pop.getMu(), random);
		return survivors;
	}

	@Override
	public boolean forceElitism() {
		return true;
	}

	@Override
	public boolean isElitist() {
		return true;
	}

}
